package de.agilecoders.wicket.settings;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * Settings interface for bootstrap settings.
 *
 * @author miha
 * @version 1.0
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
	 * @param reference
	 *            a reference to the base twitter bootstrap JavaScript library.
	 *            Defaults to the embedded bootstrap.js
	 */
    void setJsResourceReference(ResourceReference reference);
    
    /**
     * @return the jquery++ resource reference
     */
    ResourceReference getJqueryPPResourceReference();

    /**
     * @return true, if modernizr should be loaded
     */
    boolean useJqueryPP();

    /**
     * @param useJqueryPP true, if modernizr should be loaded
     */
    void useJqueryPP(final boolean useJqueryPP);

    /**
     * @return true if minification is active
     */
    boolean isMinified();

    /**
     * @param minify true, if all references should be loaded minified
     */
    void minify(final boolean minify);

    /**
     * @return true, if modernizr should be loaded
     */
    boolean useModernizr();

    /**
     * @param useModernizr true, if modernizr js library will be included
     */
    void useModernizr(final boolean useModernizr);

    /**
     * @return true, if responsive css will be included
     */
    boolean useResponsiveCss();

    /**
     * @param useResponsiveCss set to true if responsive css should be included
     */
    void useResponsiveCss(final boolean useResponsiveCss);

    /**
     * The {@link ActiveThemeProvider} provides access to the active theme
     *
     * @param themeProvider The {@link ActiveThemeProvider} instance
     */
    void setActiveThemeProvider(ActiveThemeProvider themeProvider);

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
    void setThemeProvider(ThemeProvider themeProvider);

    /**
     * @return the {@link IBootstrapLessCompilerSettings} implementation
     */
    IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings();
}
