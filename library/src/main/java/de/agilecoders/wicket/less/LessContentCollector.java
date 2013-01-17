package de.agilecoders.wicket.less;

import com.google.common.collect.Lists;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * collects all imported less files and combines them into one.
 *
 * @author miha
 */
public class LessContentCollector {
    private static final Logger LOG = LoggerFactory.getLogger(LessContentCollector.class);
    private static final Pattern IMPORT_PATTERN = Pattern.compile(".*@import\\s*\"(.*?)\".*");

    /**
     * creates a new less file that contains all imports
     *
     * @param lessResource The less source
     * @return new {@link CombinedLessResource} instance
     */
    public ICombinedLessResource collect(final ILessResource lessResource) {
        return newCombinedLessFile(lessResource);
    }

    /**
     * creates a new less file that contains all imports
     *
     * @param lessResource The less source
     * @return new {@link CombinedLessResource} instance
     */
    public Future<ICombinedLessResource> collectAsync(final ILessResource lessResource) {
        return new FutureTask<ICombinedLessResource>(new Callable<ICombinedLessResource>() {
            @Override
            public ICombinedLessResource call() throws Exception {
                return collect(lessResource);
            }
        });
    }

    /**
     * creates a new less file that contains all imports
     *
     * @param lessResource The less source
     * @return new {@link CombinedLessResource} instance
     */
    private ICombinedLessResource newCombinedLessFile(final ILessResource lessResource) {
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
    private void addAllImports(final CombinedLessResource lessResource, final ILessResource resource) {
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
     * Holder class for all less resources and their imports.
     */
    private static final class CombinedLessResource implements ICombinedLessResource {
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
            if (lastModified.before(resource.getLastModificationTime())) {
                lastModified = resource.getLastModificationTime();
            }
        }

        /**
         * @return last modification time
         */
        public final Time getLastModificationTime() {
            return lastModified;
        }

        /**
         * @return this resource as text
         */
        public final String asText() {
            if (resource != null) {
                addImport(resource);
            }

            return builder.toString();
        }

        /**
         * @return name of this resource
         */
        public final String getName() {
            return name;
        }
    }

}
