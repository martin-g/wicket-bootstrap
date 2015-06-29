package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessSource;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.util.WebJarAssetLocator;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.lang.WicketObjects;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A specialization of {@link com.github.sommeri.less4j.LessSource.URLSource}
 * that knows how to load dependencies (imports) from:
 * <ul>
 *     <li>WebJars</li>
 *     <li>classpath</li>
 *     <li>same package, different jar</li>
 * </ul>
 */
public class LessUrlSource extends LessSource.URLSource {

    private static final Logger LOG = LoggerFactory.getLogger(LessUrlSource.class);

    /**
     * A mediator class that loads a class from wicket-webjars only when a dependency
     * (an import) with scheme "webjars!" needs to be resolved
     */
    private static final class Holder {
        private static final WebJarAssetLocator locator = new WebJarAssetLocator(WicketWebjars.settings());
    }

    public static final String CLASSPATH_SCHEME = "classpath!";
    public static final String PACKAGE_SCHEME = "package!";
    public static final String WEBJARS_SCHEME = "webjars!";
    public static final String WEB_CONTEXT_SCHEME = "webcontext!";

    /**
     * The scope class used with LessResourceReference.
     * Used to resolve dependencies with scheme "package!"
     */
    private final String scopeClass;

    /**
     * Constructor
     *
     * @param inputURL The url to the Less resource
     * @param scopeClass The scope class used to load this Less resource. Also used to resolve "package!" dependencies
     */
    LessUrlSource(URL inputURL, String scopeClass) {
        super(inputURL);

        this.scopeClass = scopeClass;
    }

    @Override
    public LessSource relativeSource(String filename) throws FileNotFound, CannotReadFile {
        final LessSource relative;
        boolean addParent = true;

        if (StringUtils.startsWith(filename, WEBJARS_SCHEME)) {
            relative = resolveWebJarsDependency(filename);
        } else if (StringUtils.startsWith(filename, CLASSPATH_SCHEME)) {
            relative = resolveClasspathDependency(filename);
        } else if (StringUtils.startsWith(filename, WEB_CONTEXT_SCHEME)) {
            relative = resolveWebContextDependency(filename);
        } else if (scopeClass != null && StringUtils.startsWith(filename, PACKAGE_SCHEME)) {
            relative = resolvePackageDependency(filename);
        } else {
            addParent = false;
            relative = super.relativeSource(filename);
        }

        if (addParent) {
            // add imported source to detect correct last modified time
            addImportedSource(relative);
        }

        return relative;
    }

    private LessSource resolveWebContextDependency(String filename) {
        LOG.debug("Going to resolve an import from the web context: {}", filename);
        String resourceName = filename.substring(WEB_CONTEXT_SCHEME.length());
        if (resourceName.indexOf(0) == '/') {
            resourceName = resourceName.substring(1);
        }

        final ServletContext context = ((WebApplication) Application.get()).getServletContext();
        URL url;
        try {
            url = context.getResource(resourceName);
            return new LessUrlSource(url, scopeClass);
        } catch (MalformedURLException mux) {
            throw new IllegalArgumentException("Cannot create a URL to a resource in the web context", mux);
        }
    }

    private LessUrlSource resolvePackageDependency(String filename) {
        if (Strings.isEmpty(scopeClass)) {
            throw new IllegalStateException("Cannot resolve dependency '" + filename + "' without a scope class!");
        }

        LOG.debug("Going to resolve an import from the package: {}", filename);
        String resourceName = filename.substring(PACKAGE_SCHEME.length());
        if (resourceName.indexOf(0) == '/') {
            resourceName = resourceName.substring(1);
        }

        Class<?> scope = WicketObjects.resolveClass(scopeClass);
        URL url = scope.getResource(resourceName);
        if (url != null) {
            return new LessUrlSource(url, scopeClass);
        } else {
            throw new IllegalArgumentException(
                    String.format("Cannot resolve relative source with name '%s' in the package of '%s'", filename, scopeClass));
        }
    }

    private LessUrlSource resolveClasspathDependency(String filename) {
        LOG.debug("Going to resolve an import from the classpath: {}", filename);
        String resourceName = filename.substring(CLASSPATH_SCHEME.length() + 1);
        if (resourceName.indexOf(0) != '/') {
            resourceName = '/' + resourceName;
        }

        URL url = LessCacheManager.class.getResource(resourceName);
        if (url != null) {
            return new LessUrlSource(url, scopeClass);
        } else {
            throw new IllegalArgumentException(
                    String.format("Cannot resolve relative source with name '%s' in the classpath", filename));
        }
    }

    private LessUrlSource resolveWebJarsDependency(String filename) {
        LOG.debug("Going to resolve an import from WebJars: {}", filename);
        final String file = Holder.locator.getFullPath(filename.replaceFirst(WEBJARS_SCHEME, "/webjars/"));

        try {
            final URL res = Thread.currentThread().getContextClassLoader().getResource(file);

            return new LessUrlSource(res, scopeClass);
        } catch (RuntimeException e) {
            throw new WicketRuntimeException(e);
        }
    }
}
