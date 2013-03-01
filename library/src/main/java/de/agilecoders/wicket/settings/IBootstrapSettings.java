package de.agilecoders.wicket.settings;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * Settings interface for bootstrap settings.
 *
 * @author miha
 */
public interface IBootstrapSettings {

    /**
     * @return the base twitter bootstrap css resource reference
     */
    ResourceReference getCssResourceReference();

    /**
     * @return the twitter bootstrap responsive css resource reference
     */
    ResourceReference getResponsiveCssResourceReference();

    /**
     * @return the base twitter bootstrap JavaScript resource reference
     */
    ResourceReference getJsResourceReference();

    /**
     * @param reference a reference to the base twitter bootstrap JavaScript library.
     *                  Defaults to the embedded bootstrap.js
     * @return same instance for chaining
     */
    IBootstrapSettings setJsResourceReference(ResourceReference reference);

    /**
     * @return the jquery++ resource reference
     */
    ResourceReference getJqueryPPResourceReference();

    /**
     * @return true, if modernizr should be loaded
     */
    boolean useJqueryPP();

    /**
     * @return javascript resource filter name
     */
    String getJsResourceFilterName();

    /**
     * @param useJqueryPP true, if modernizr should be loaded
     * @return same instance for chaining
     */
    IBootstrapSettings useJqueryPP(final boolean useJqueryPP);

    /**
     * @return true if minification is active
     */
    boolean isMinified();

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
     * @param minify true, if all references should be loaded minified
     * @return same instance for chaining
     */
    IBootstrapSettings minify(final boolean minify);

    /**
     * @return true, if modernizr should be loaded
     */
    boolean useModernizr();

    /**
     * @param useModernizr true, if modernizr js library will be included
     * @return same instance for chaining
     */
    IBootstrapSettings useModernizr(final boolean useModernizr);

    /**
     * if true, all necessary exceptions will be added to security manager to allow
     * fonts and less files. (default is true)
     *
     * @return true, if security manger should be updated while installing these settings
     */
    boolean updateSecurityManager();

    /**
     * @return true, if responsive css will be included
     */
    boolean useResponsiveCss();

    /**
     * @param useResponsiveCss set to true if responsive css should be included
     * @return same instance for chaining
     */
    IBootstrapSettings useResponsiveCss(final boolean useResponsiveCss);

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
     * @return the {@link IBootstrapLessCompilerSettings} implementation
     */
    IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings();
}
