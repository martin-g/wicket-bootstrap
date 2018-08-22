package de.agilecoders.wicket.core.settings;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * #### Description
 *
 * Settings interface for bootstrap settings.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public interface IBootstrapSettings {

    /**
     * The version of Bootstrap
     */
    String VERSION = "4.1.0";

    /**
     * The version of modernizr
     */
    String MODERNIZR_VERSION = "2.8.3";

    /**
     * The version of Popper.js
     */
    String POPPER_VERSION = "1.14.1";

    /**
     * The url to the JavaScript resource at a CDN network
     */
    String JS_CDN_PATTERN = "//maxcdn.bootstrapcdn.com/bootstrap/%s/js/bootstrap.min.js";

    /**
     * The url to the modernizr JavaScript resource at a CDN network
     */
    String MODERNIZR_CDN_PATTERN = "//cdnjs.cloudflare.com/ajax/libs/modernizr/%s/modernizr.min.js";

    /**
     * The url to the css resource at a CDN network
     */
    String CSS_CDN_PATTERN = "//maxcdn.bootstrapcdn.com/bootstrap/%s/css/bootstrap.min.css";

    /**
     * The url to the Popper.js Javascript resource at a CDN network
     */
    String POPPER_JS_CDN_PATTERN = "//unpkg.com/popper.js@%s/dist/umd/popper.min.js";

    /**
     * @param version The version of Bootstrap. CDN resources use it to construct their urls
     * @return same instance for chaining
     */
    IBootstrapSettings setVersion(String version);

    /**
     * @param version The version of modernizr. CDN resources use it to construct their urls
     * @return same instance for chaining
     */
    IBootstrapSettings setModernizrVersion(String version);

    /**
     * @param version The version of popper.js. CDN resources use it to construct their urls
     * @return same instance for chaining
     */
    IBootstrapSettings setPopperJsVersion(String version);

    /**
     * @return the deferJavascript flag
     */
    boolean deferJavascript();

    /**
     * @return The version of Bootstrap. CDN resources use it to construct their urls
     */
    String getVersion();

    /**
     * @return The version of modernizr. CDN resources use it to construct their urls
     */
    String getModernizrVersion();

    /**
     * @return The version of popper.js. CDN resources use it to construct their urls
     */
    String getPopperJsVersion();

    /**
     * @return True, if bootstrap resources should be added to each page automatically
     */
    boolean autoAppendResources();

    /**
     * @return the base bootstrap css resource reference
     */
    ResourceReference getCssResourceReference();

    /**
     * @return the base bootstrap JavaScript resource reference
     */
    ResourceReference getJsResourceReference();

    /**
     * @return the modernizr JavaScript resource reference
     */
    ResourceReference getModernizrResourceReference();

    /**
     * @return the popper.js JavaScript resource reference
     */
    ResourceReference getPopperJsResourceReference();

    /**
     * @param reference a reference to the base bootstrap css library.
     *                  Defaults to the embedded bootstrap.css
     * @return same instance for chaining
     */
    IBootstrapSettings setCssResourceReference(ResourceReference reference);

    /**
     * @param reference a reference to the base bootstrap JavaScript library.
     *                  Defaults to the embedded bootstrap.js
     * @return same instance for chaining
     */
    IBootstrapSettings setJsResourceReference(ResourceReference reference);

    /**
     * @param reference a reference to the Modernizr library.
     *                  Defaults to the embedded modernizr.js
     * @return same instance for chaining
     */
    IBootstrapSettings setModernizrResourceReference(ResourceReference reference);

    /**
     *
     * @param popperJsResourceReference a reference to the Popper.js library.
     *                                  Defaults to the embedded popper.js.
     * @return same instance for chaining
     */
    IBootstrapSettings setPopperJsResourceReference(ResourceReference popperJsResourceReference);

    /**
     * @return javascript resource filter name
     */
    String getJsResourceFilterName();

    /**
     * set value to true, if bootstrap resources should be added to each page automatically
     *
     * @param value true, if bootstrap resources should be added to each page automatically
     * @return same instance for chaining
     */
    IBootstrapSettings setAutoAppendResources(boolean value);

    /**
     * sets the filter name for all bootstrap js resource references
     *
     * @param name javascript resource filter name
     * @return same instance for chaining
     */
    IBootstrapSettings setJsResourceFilterName(String name);

    /**
     * if true, all necessary exceptions will be added to security manager to allow
     * fonts and less files. (default is true)
     *
     * @param activate true, if security manger should be updated while installing these settings
     * @return same instance for chaining
     */
    IBootstrapSettings setUpdateSecurityManager(boolean activate);

    /**
     * if true, the <script/> tag for the bootstrap javascript will get the defer="defer" attribute
     *
     * @param defer
     * @return same instance for chaining
     */
    IBootstrapSettings setDeferJavascript(boolean defer);

    /**
     * if true, all necessary exceptions will be added to security manager to allow
     * fonts and less files. (default is true)
     *
     * @return true, if security manger should be updated while installing these settings
     */
    boolean updateSecurityManager();

    /**
     * The {@link ActiveThemeProvider} provides access to the active theme
     *
     * @param themeProvider The {@link ActiveThemeProvider} instance
     * @return same instance for chaining
     */
    IBootstrapSettings setActiveThemeProvider(ActiveThemeProvider themeProvider);

    /**
     * @return The {@link ActiveThemeProvider} instance
     */
    ActiveThemeProvider getActiveThemeProvider();

    /**
     * @return The {@link ThemeProvider} instance
     */
    ThemeProvider getThemeProvider();

    /**
     * The {@link ThemeProvider} instance provides access to all available themes.
     *
     * @param themeProvider The {@link ThemeProvider} instance
     * @return same instance for chaining
     */
    IBootstrapSettings setThemeProvider(ThemeProvider themeProvider);

    /**
     * @return true, if the resources for the themes should be loaded from a CDN network
     */
    boolean useCdnResources();

    /**
     * @return true, if wicket bootstrap uses the webjars library. If you don't want to use the webjars libraries,
     * please set bootstrap css/js, modernizr reference to your own instance or call {@link #useCdnResources(boolean)}.
     * Some components uses webjars references internally, so if you want to use them,
     * please override the provided {@code newXYHeaderItem()} methods.
     */
    boolean useWebjars();

    /**
     * @param useCdnResources a flag indicating whether the resources for the themes should be loaded from a CDN network
     * @return this instance
     */
    IBootstrapSettings useCdnResources(boolean useCdnResources);
}
