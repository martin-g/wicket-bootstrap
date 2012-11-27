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
import org.apache.wicket.util.lang.Exceptions;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * @version 1.0
 */
public class BootstrapLessCompiler implements IBootstrapLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(BootstrapLessCompiler.class);

    /**
     * Use http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * to load LessEngine if it is really needed
     */
    private static class LessEngineHolder {
        private final static LessEngine instance = new LessEngine(Bootstrap.getSettings(Application.get()).getBootstrapLessCompilerSettings().getLessOptions());
    }

    private static LessEngine getLessEngine() {
        return LessEngineHolder.instance;
    }

    private static final Pattern IMPORT_PATTERN = Pattern.compile(".*@import\\s*\"(.*?)\".*");

    private Application app() {
        return Application.get();
    }

    @Override
    public byte[] generate(LessCompilable lessCompilable) {
        return compile(newCombinedLessFile(lessCompilable));
    }

    @Override
    public Time lastModifiedRecursive(LessCompilable lessCompilable) {
        return newCombinedLessFile(lessCompilable).lastModified();
    }

    private CombinedLessResource newCombinedLessFile(LessCompilable lessCompilable) {
        CombinedLessResource lessResource = new CombinedLessResource();

        for (Resource resource : lessCompilable.getLessResources()) {
            addAllImports(lessResource, resource);
        }

        return lessResource;
    }

    private void addAllImports(CombinedLessResource lessResource, Resource resource) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;

            while ((line = r.readLine()) != null) {
                Matcher m = IMPORT_PATTERN.matcher(line);

                while (m.find()) {
                    Resource file = resource.getRelative(m.group(1));
                    if (!file.exists() && !m.group(1).endsWith(".less")) {
                        file = resource.getRelative(m.group(1) + ".less");
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
    protected byte[] compile(CombinedLessResource lessFile) {
        try {
            return getLessEngine().compile(lessFile.createText()).getBytes(charset());
        } catch (LessException e) {
            return handleException(e);
        }
    }

    /**
     * @return the charset to use for {@code BootstrapLessCompiler}
     */
    protected final Charset charset() {
        return settings().getBootstrapLessCompilerSettings().getCharset();
    }

    /**
     * @return the settings to use for {@code BootstrapLessCompiler}
     */
    protected final IBootstrapSettings settings() {
        return Bootstrap.getSettings(app());
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    protected byte[] handleException(LessException e) {
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
        private final Resource resource;
        private final StringBuilder builder;
        private Time lastModified;

        /**
         * Construct.
         */
        private CombinedLessResource() {
            this(null);
        }

        private CombinedLessResource(final Resource resource) {
            this.resource = resource;
            this.builder = new StringBuilder();
            this.lastModified = Time.START_OF_UNIX_TIME;
        }

        private void addImport(Resource resource) {
            if (!cache.contains(resource.getPath())) {
                builder.append(IMPORT_PATTERN.matcher(resource.asText()).replaceAll(""));
                updateLastModified(resource);
                cache.add(resource.getPath());
            }
        }

        private void updateLastModified(Resource resource) {
            if (lastModified.before(resource.lastModified())) {
                lastModified = resource.lastModified();
            }
        }

        private Time lastModified() {
            return lastModified;
        }

        private String createText() {
            if (resource != null) {
                addImport(resource);
            }

            return builder.toString();
        }

    }
}
