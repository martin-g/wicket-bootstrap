package de.agilecoders.wicket.less;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.lang.Exceptions;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An {@link IBootstrapLessCompiler} implementation that loads all less files and their
 * imports and compiles them to an optimized css stream.
 *
 * @author miha
 */
public class LessJsLessCompiler implements IBootstrapLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(LessJsLessCompiler.class);

    private static final Pattern IMPORT_PATTERN = Pattern.compile(".*@import\\s*\"(.*?)\".*");

    /**
     * Use http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * to load LessEngine if it is really needed
     */
    private static class LessEngineHolder {
        private final static LessEngine instance = new LessEngine(Bootstrap.getSettings().getBootstrapLessCompilerSettings().getLessOptions());
    }

    /**
     * @return current less engine
     */
    private static LessEngine getLessEngine() {
        return LessEngineHolder.instance;
    }

    /**
     * @return current active application
     */
    private Application app() {
        return Application.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICompiledResource compile(final ILessResource lessResource) {
        final long start = System.currentTimeMillis();

        try {
            return compile(newCombinedLessFile(lessResource));
        } finally {
            LOG.debug("duration of collect imports for {}: {} ms", lessResource.getName(), System.currentTimeMillis() - start);
        }
    }

    /**
     * creates a new less file that contains all imports
     *
     * @param lessResource The less source
     * @return new {@link CombinedLessResource} instance
     */
    private CombinedLessResource newCombinedLessFile(final ILessResource lessResource) {
        final CombinedLessResource combinedLessResource = new CombinedLessResource(lessResource);
        addAllImports(combinedLessResource, lessResource);

        return combinedLessResource;
    }

    /**
     * collects all imports (recursively) and appends the data to given less resource
     *
     * @param lessResource The combined less resource
     * @param resource     the base less resource
     */
    private void addAllImports(CombinedLessResource lessResource, ILessResource resource) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;

            while ((line = r.readLine()) != null) {
                Matcher m = IMPORT_PATTERN.matcher(line);

                while (m.find()) {
                    LessResource file = resource.getRelative(m.group(1));
                    if (!file.exists() && !m.group(1).endsWith(ILessResource.LESS_EXTENSION)) {
                        file = resource.getRelative(m.group(1) + ILessResource.LESS_EXTENSION);
                    }

                    addAllImports(lessResource, file);

                    lessResource.addImport(file);
                }
            }
            lessResource.addImport(resource);
        } catch (IOException e) {
            LOG.error("{}", e);
        } finally {
            IOUtils.closeQuietly(r);
        }
    }

    /**
     * calls the underlying less compiler (less.js/Rhino) and compiles the less
     * content.
     *
     * @param lessFile the {@link CombinedLessResource}
     * @return generated css as byte array
     */
    protected ICompiledResource compile(CombinedLessResource lessFile) {
        return new CompiledResource(lessFile) {
            @Override
            protected byte[] compile(final CombinedLessResource lessFile) {
                final long start = System.currentTimeMillis();
                try {
                    return getLessEngine().compile(lessFile.asText()).getBytes(charset());
                } catch (LessException e) {
                    return handleException(e);
                } finally {
                    LOG.debug("duration of collect imports for {}: {} ms", lessFile.getName(), System.currentTimeMillis() - start);
                }
            }
        };
    }

    /**
     * @return the charset to use for {@code LessJsLessCompiler}
     */
    protected final Charset charset() {
        return settings().getBootstrapLessCompilerSettings().getCharset();
    }

    /**
     * @return the settings to use for {@code LessJsLessCompiler}
     */
    protected final IBootstrapSettings settings() {
        return Bootstrap.getSettings(app());
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private byte[] handleException(LessException e) {
        LOG.error("Less exception", e);

        String filename = e.getFilename();
        List<String> extractList = e.getExtract();

        String extract = null;
        if (extractList != null) {
            extract = Joiner.on(",").join(extractList);
        }

        // Try to detect missing imports
        if (Strings.isNullOrEmpty(extract)) {
            FileNotFoundException fileNotFoundException = Exceptions.findCause(e, FileNotFoundException.class);

            if (fileNotFoundException != null) {
                extract = fileNotFoundException.getMessage();
            }
        }

        throw new WicketRuntimeException("can't parse " + filename + "; error in line " + e.getLine() + " on column " + e.getColumn() + "; " + e.getType() + ": " + extract);
    }

    /**
     * Holder class for all less resources and their imports.
     */
    private static final class CombinedLessResource {
        private final List<String> cache = Lists.newArrayList();
        private final ILessResource resource;
        private final StringBuilder builder;
        private Time lastModified;
        private final String name;

        /**
         * Construct.
         *
         * @param resource The wrapped less resource
         */
        private CombinedLessResource(final ILessResource resource) {
            this.name = resource.getName();
            this.resource = resource;
            this.builder = new StringBuilder();
            this.lastModified = Time.START_OF_UNIX_TIME;
        }

        /**
         * adds an import to this resource
         *
         * @param resource The import to add
         */
        private void addImport(ILessResource resource) {
            if (!cache.contains(resource.getPath())) {
                builder.append(IMPORT_PATTERN.matcher(resource.asText()).replaceAll(""));
                updateLastModified(resource);
                cache.add(resource.getPath());
            }
        }

        /**
         * updates the last modification time
         *
         * @param resource the recently added import
         */
        private void updateLastModified(ILessResource resource) {
            if (lastModified.before(resource.lastModified())) {
                lastModified = resource.lastModified();
            }
        }

        /**
         * @return last modification time
         */
        public Time lastModified() {
            return lastModified;
        }

        /**
         * @return this resource as text
         */
        private String asText() {
            if (resource != null) {
                addImport(resource);
            }

            return builder.toString();
        }

        /**
         * @return name of this resource
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Default implementation of {@link ICompiledResource}
     */
    private static abstract class CompiledResource implements ICompiledResource {

        private final CombinedLessResource lessFile;

        private byte[] content;
        private Bytes length;

        /**
         * Construct.
         *
         * @param lessFile the last modification time
         */
        private CompiledResource(final CombinedLessResource lessFile) {
            this.lessFile = lessFile;
        }

        @Override
        public final Time getLastModificationTime() {
            return lessFile.lastModified();
        }

        @Override
        public final Bytes length() {
            return length;
        }

        @Override
        public final InputStream getInputStream() {
            if (content == null) {
                content = compile(lessFile);
                length = Bytes.bytes(content.length);
            }
            return new ByteArrayInputStream(content);
        }

        /**
         * compiles and returns less content
         *
         * @return the compiled less content
         */
        protected abstract byte[] compile(final CombinedLessResource lessFile);
    }
}
