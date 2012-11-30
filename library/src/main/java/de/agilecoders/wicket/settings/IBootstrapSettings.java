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
     */
    IBootstrapSettings setJsResourceFilterName(final String name);

    /**
     * @param minify true, if all references should be loaded minified
     */
    IBootstrapSettings minify(final boolean minify);

    /**
     * @return true, if modernizr should be loaded
     */
    boolean useModernizr();

    /**
     * @param useModernizr true, if modernizr js library will be included
     */
    IBootstrapSettings useModernizr(final boolean useModernizr);

    /**
     * @return true, if responsive css will be included
     */
    boolean useResponsiveCss();

    /**
     * @param useResponsiveCss set to true if responsive css should be included
     */
    IBootstrapSettings useResponsiveCss(final boolean useResponsiveCss);

    /**
     * The {@link ActiveThemeProvider} provides access to the active theme
     *
     * @param themeProvider The {@link ActiveThemeProvider} instance
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
     */
    IBootstrapSettings setThemeProvider(ThemeProvider themeProvider);

    /**
     * @return the {@link IBootstrapLessCompilerSettings} implementation
     */
    IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings();
}
