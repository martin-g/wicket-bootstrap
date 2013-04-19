package de.agilecoders.wicket.core.settings;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * Settings interface for bootstrap settings.
 *
 * @author miha
 */
public interface IBootstrapSettings {

    /**
     * The version of Bootstrap
     */
    String VERSION = "2.3.1";

    /**
     * @param version The version of Twitter Bootstrap. CDN resources use it to construct their urls
     * @return same instance for chaining
     */
    IBootstrapSettings setVersion(String version);

    /**
     * @return The version of Twitter Bootstrap. CDN resources use it to construct their urls
     */
    String getVersion();

    /**
     * @return the base twitter bootstrap css resource reference
     */
    ResourceReference getCssResourceReference();

    /**
     * @return the base twitter bootstrap JavaScript resource reference
     */
    ResourceReference getJsResourceReference();

    /**
     * @param reference a reference to the base twitter bootstrap css library.
     *                  Defaults to the embedded bootstrap-responsive.css
     * @return same instance for chaining
     */
    IBootstrapSettings setCssResourceReference(ResourceReference reference);

    /**
     * @param reference a reference to the base twitter bootstrap JavaScript library.
     *                  Defaults to the embedded bootstrap.js
     * @return same instance for chaining
     */
    IBootstrapSettings setJsResourceReference(ResourceReference reference);

    /**
     * @return javascript resource filter name
     */
    String getJsResourceFilterName();

    /**
     * sets the filter name for all bootstrap js resource references
     *
     * @param name javascript resource filter name
     * @return same instance for chaining
     */
    IBootstrapSettings setJsResourceFilterName(final String name);

    /**
     * if true, all necessary exceptions will be added to security manager to allow
     * fonts and less files. (default is true)
     *
     * @param activate true, if security manger should be updated while installing these settings
     * @return same instance for chaining
     */
    IBootstrapSettings setUpdateSecurityManager(final boolean activate);

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
     * @param useCdnResources a flag indicating whether the resources for the themes should be loaded from a CDN network
     * @return this instance
     */
    IBootstrapSettings useCdnResources(boolean useCdnResources);
}
