package de.agilecoders.wicket.settings;

import org.apache.wicket.Application;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.JqueryPPJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;

/**
 * @author miha
 * @version 1.0
 * @see IBootstrapSettings
 */
public class BootstrapSettings implements IBootstrapSettings {

	private ResourceReference bootstrapJavaScriptReference = BootstrapJavaScriptReference.get();
	
	private boolean useResponsiveCss = true;
    private ThemeProvider themeProvider = new BootswatchThemeProvider();
    private ActiveThemeProvider activeThemeProvider = new SessionThemeProvider();
    private boolean useModernizr = false;
    private boolean useJqueryPP = false;
    private IBootstrapLessCompilerSettings bootstrapLessCompilerSettings = new BootstrapLessCompilerSettings();

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
        return bootstrapJavaScriptReference;
    }
    
    @Override
    public void setJsResourceReference(final ResourceReference bootstrapJavaScriptReference) {
    	this.bootstrapJavaScriptReference = bootstrapJavaScriptReference;
    }

    @Override
    public ResourceReference getJqueryPPResourceReference() {
        return JqueryPPJavaScriptReference.INSTANCE;
    }

    @Override
    public boolean useJqueryPP() {
        return useJqueryPP;
    }

    @Override
    public void useJqueryPP(boolean useJqueryPP) {
        this.useJqueryPP = useJqueryPP;
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
    public void useModernizr(boolean useModernizr) {
        this.useModernizr = useModernizr;
    }

    @Override
    public boolean useResponsiveCss() {
        return useResponsiveCss;
    }

    @Override
    public final void useResponsiveCss(final boolean useResponsiveCss) {
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

    @Override
    public IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings() {
        return bootstrapLessCompilerSettings;
    }
}
