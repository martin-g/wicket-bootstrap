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
     * @return returns the jquery cdn url
     */
    String getJqueryUrl();

    void setJqueryUrl(final String url);

    boolean isMinified();

    void minify(final boolean minify);

    boolean useResponsiveCss();

    void setUseResponsiveCss(final boolean useResponsiveCss);

    void setActiveThemeProvider(ActiveThemeProvider themeProvider);

    ActiveThemeProvider getActiveThemeProvider();

    ThemeProvider getThemeProvider();

    void setThemeProvider(ThemeProvider themeProvider);
}
