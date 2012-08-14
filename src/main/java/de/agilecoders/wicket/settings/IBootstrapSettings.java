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
     * @return the base twitter bootstrap javascript resource reference
     */
    ResourceReference getJsResourceReference();

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

    void useModernizr(final boolean useModernizr);

    boolean useResponsiveCss();

    void useResponsiveCss(final boolean useResponsiveCss);

    void setActiveThemeProvider(ActiveThemeProvider themeProvider);

    ActiveThemeProvider getActiveThemeProvider();

    ThemeProvider getThemeProvider();

    void setThemeProvider(ThemeProvider themeProvider);

    /**
     * @return the {@link IBootstrapLessCompilerSettings} implementation
     */
    IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings();
}
