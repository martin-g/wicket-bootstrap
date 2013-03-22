package de.agilecoders.wicket.less;

import de.agilecoders.wicket.BootstrapLess;
import org.apache.wicket.Application;
import org.apache.wicket.core.util.resource.locator.ResourceStreamLocator;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.file.IResourceFinder;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.resource.AbstractResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A stream locator that automatically compiles ".less" files. This {@link ResourceStreamLocator}
 * should only be used in development mode (this is the default configuration for
 * {@link de.agilecoders.wicket.less.IBootstrapLessCompilerSettings}), because it will check the modification
 * time of each less file for each request once.
 *
 * @author miha
 */
public class LessResourceStreamLocator extends ResourceStreamLocator {
    private static final Logger LOG = LoggerFactory.getLogger(LessResourceStreamLocator.class);

    /**
     * thread lookup set that holds a list of all processed resource keys during a web request
     */
    private final ThreadLocal<Set<String>> THREAD_CACHE = new ThreadLocal<Set<String>>() {
        @Override
        protected Set<String> initialValue() {
            return new HashSet<String>();
        }
    };
    private final ConcurrentMap<String, CacheValue> CACHE = new ConcurrentHashMap<String, CacheValue>();

    /**
     * Constructor
     */
    public LessResourceStreamLocator() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param finders resource finders. These will be tried in the given order.
     */
    public LessResourceStreamLocator(final List<IResourceFinder> finders) {
        super(finders);
    }

    @Override
    public IResourceStream locate(Class<?> clazz, String path) {
        return super.locate(clazz, path);
    }

    @Override
    public IResourceStream locate(Class<?> clazz, String path, String style, String variation, Locale locale, String extension, boolean strict) {
        if (isActive(clazz, variation)) {
            return fromCacheOrLoadAndCompile(clazz, path);
        } else {
            return super.locate(clazz, path, style, variation, locale, extension, strict);
        }
    }

    /**
     * creates a new cache key
     *
     * @param clazz The class loader for delegating the loading of the resource
     * @param path  The path of the resource
     * @return new cache key
     */
    private static String createKey(final Class<?> clazz, final String path) {
        return clazz.getName() + "##" + path;
    }

    /**
     * returns value from cache or load it
     *
     * @param clazz The class loader for delegating the loading of the resource
     * @param path  The path of the resource
     * @return cached value or loaded one
     */
    private IResourceStream fromCacheOrLoadAndCompile(final Class<?> clazz, final String path) {
        final long startCache = System.currentTimeMillis();
        final String key = createKey(clazz, path).intern();
        final LessResourceStream lessResourceStream;

        /**
         * a lock on reference class should be ok, other {@link ResourceReference} scopes shouldn't be
         * locked here.
         *
         * Each call needs two different steps, first one is collecting all less files and their content (this
         * includes the last modification time) and the second one (this is the expensive part) is compiling
         * the content. Because of this there are two different caches, the L1 cache holds all look up data
         * for current thread, this reduces disk io because the outdated check is performed once for each thread.
         * The L2 cache holds the compiled content as long as the application is active and data isn't out
         * dated.
         */
        synchronized (key) {
            final CacheValue cacheValue = CACHE.get(key);

            /**
             * This {@link THREAD_CACHE} is necessary to reduce look ups for outdated
             * less resources during a single request.
             */
            if (THREAD_CACHE.get().contains(key) && cacheValue != null) {
                LOG.debug("L1 cache hit: loading of {} from L1 cache took {} ms", path, System.currentTimeMillis() - startCache);
                return cacheValue.original;
            } else {
                THREAD_CACHE.get().add(key);
            }

            lessResourceStream = loadStream(clazz, path);

            // check for outdated less resources, if all are up2date return cache value directly
            // without changing the cache.
            if (cacheValue != null && cacheValue.isUpToDate(lessResourceStream.lastModifiedTime())) {
                LOG.debug("L2 cache hit: loading of {} from L2 cache took {} ms", path, System.currentTimeMillis() - startCache);

                return cacheValue.original;
            }

            // put a new cache value to cache.
            CACHE.put(key, new CacheValue(lessResourceStream));
        }

        LOG.debug("cache miss: loading of {} took {} ms", path, System.currentTimeMillis() - startCache);
        return lessResourceStream;
    }

    /**
     * Simple cache value that holds an {@link IResourceStream}.
     */
    private static final class CacheValue implements IClusterable {
        private final IResourceStream original;
        private final Time modificationTime;

        /**
         * Construct.
         *
         * @param original The {@link IResourceStream} to cache
         */
        private CacheValue(final IResourceStream original) {
            this.original = original;
            this.modificationTime = original.lastModifiedTime();
        }

        /**
         * checks if given time is less or equal than this
         *
         * @param time The time to check against
         * @return true, if given time is less or equal than current
         */
        public boolean isUpToDate(Time time) {
            return time == null || time.lessThanOrEqual(modificationTime);
        }
    }

    /**
     * loads a {@link LessResourceStream} according to given clazz/path combination
     *
     * @param clazz The class loader for delegating the loading of the resource
     * @param path  The path of the resource
     * @return new {@link LessResourceStream} instance
     */
    private LessResourceStream loadStream(final Class<?> clazz, final String path) {
        final long start = System.currentTimeMillis();
        try {
            return new LessResourceStream(compile(clazz, path.replace(ILessResource.CSSMIN_EXTENSION, ILessResource.LESS_EXTENSION)
                    .replace(ILessResource.CSS_EXTENSION, ILessResource.LESS_EXTENSION)));
        } finally {
            LOG.debug("loading stream of {} took {} ms", path, System.currentTimeMillis() - start);
        }
    }

    /**
     * @return true if less compiler should be used.
     */
    private static boolean useLessCompiler() {
        return BootstrapLess.getSettings().useLessCompiler();
    }

    /**
     * checks whether less compiler is active for this resource or not.
     *
     * @param clazz mandatory parameter
     * @param variation  mandatory parameter
     * @return true if less compiler should be used.
     */
    private static boolean isActive(final Class clazz, final String variation) {
        return useLessCompiler() && CssResourceReference.class.isAssignableFrom(clazz) &&
               LessResourceReference.VARIATION.equals(variation);
    }

    /**
     * triggers the compilation of given resource
     *
     * @param clazz mandatory parameter
     * @param path  mandatory parameter
     * @return a new compiler result
     */
    private static ICompiledResource compile(final Class<?> clazz, final String path) {
        return getCompiler().compile(new LessResource(clazz, path));
    }

    /**
     * By default returns the configured compiler on
     * application level, but can be overridden by the user application to provide compiler
     * specific to the resource.
     *
     * @return the configured application level less compiler. May be {@code null}.
     */
    private static IBootstrapLessCompiler getCompiler() {
        if (Application.exists()) {
            return BootstrapLess.getSettings().getLessCompiler();
        }

        throw new IllegalStateException("there is no application assigned to current thread.");
    }

    /**
     * An {@link IResourceStream} implementation for less resources
     */
    public static final class LessResourceStream extends AbstractResourceStream {
        private final ICompiledResource compiledResource;

        /**
         * Construct.
         *
         * @param compiledResource The compiled resource
         */
        public LessResourceStream(ICompiledResource compiledResource) {
            this.compiledResource = compiledResource;
        }

        @Override
        public String getContentType() {
            return "text/css";
        }

        @Override
        public Bytes length() {
            return compiledResource.length();
        }

        @Override
        public InputStream getInputStream() throws ResourceStreamNotFoundException {
            return compiledResource.getInputStream();
        }

        @Override
        public void close() throws IOException {
            compiledResource.close();
        }

        @Override
        public Time lastModifiedTime() {
            return compiledResource.getModificationTime();
        }

        @Override
        public String getVariation() {
            return LessResourceReference.VARIATION;
        }
    }
}