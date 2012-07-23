package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.references.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.BootstrapResponsiveCssReference;
import org.apache.wicket.Application;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * @see IBootstrapSettings
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapSettings implements IBootstrapSettings {

    private boolean minify = false;
    private String jqueryUrl = "//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js";
    private boolean useResponsiveCss = true;
    private ThemeProvider themeProvider = new BootswatchThemeProvider();
    private ActiveThemeProvider activeThemeProvider = new SessionThemeProvider(themeProvider);
    private boolean useModernizr = false;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        // so far nothing to do here
    }

    @Override
    public ResourceReference getCssResourceReference() {
        return BootstrapCssReference.INSTANCE;
    }

    @Override
    public CssResourceReference getResponsiveCssResourceReference() {
        return BootstrapResponsiveCssReference.INSTANCE;
    }

    @Override
    public ResourceReference getJsResourceReference() {
        return BootstrapJavaScriptReference.INSTANCE;
    }

    @Override
    public String getJqueryUrl() {
        return jqueryUrl;
    }

    @Override
    public final void setJqueryUrl(final String url) {
        jqueryUrl = url;
    }

    @Override
    public boolean isMinified() {
        return Application.get().getResourceSettings().getUseMinifiedResources();
    }

    @Override
    public final void minify(final boolean minify) {
        Application.get().getResourceSettings().setUseMinifiedResources(minify);
    }

    @Override
    public boolean useModernizr() {
        return useModernizr;
    }

    @Override
    public void setUseModernizr(boolean useModernizr) {
        this.useModernizr = useModernizr;
    }

    @Override
    public boolean useResponsiveCss() {
        return useResponsiveCss;
    }

    @Override
    public final void setUseResponsiveCss(final boolean useResponsiveCss) {
        this.useResponsiveCss = useResponsiveCss;
    }

    @Override
    public ActiveThemeProvider getActiveThemeProvider() {
        return activeThemeProvider;
    }

    @Override
    public void setActiveThemeProvider(ActiveThemeProvider activeThemeProvider) {
        this.activeThemeProvider = activeThemeProvider;
    }

    @Override
    public ThemeProvider getThemeProvider() {
        return themeProvider;
    }

    @Override
    public void setThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
    }
}
