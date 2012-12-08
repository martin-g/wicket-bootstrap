package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.JqueryPPJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
import org.apache.wicket.Application;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Default {@link IBootstrapSettings} implementation
 *
 * @author miha
 * @see IBootstrapSettings
 */
public class BootstrapSettings implements IBootstrapSettings {

    private ResourceReference bootstrapJavaScriptReference = BootstrapJavaScriptReference.get();

    private boolean useResponsiveCss = true;
    private ThemeProvider themeProvider = new BootswatchThemeProvider();
    private ActiveThemeProvider activeThemeProvider = new SessionThemeProvider();
    private boolean useModernizr = false;
    private boolean useJqueryPP = false;
    private String resourceFilterName = "";
    private IBootstrapLessCompilerSettings bootstrapLessCompilerSettings = new BootstrapLessCompilerSettings();
    private boolean updateSecurityManger = true;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        // so far nothing to do here
    }

    /**
     * Construct. Sets some configuration mode specific settings.
     *
     * @param application The current application instance
     */
    public BootstrapSettings(final Application application) {
        this();

        final boolean usesDeploymentConfig = application.usesDeploymentConfig();

        minify(usesDeploymentConfig);
        getBootstrapLessCompilerSettings().setUseLessCompiler(!usesDeploymentConfig);
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
    public BootstrapSettings setJsResourceReference(final ResourceReference bootstrapJavaScriptReference) {
        this.bootstrapJavaScriptReference = bootstrapJavaScriptReference;
        return this;
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
    public String getJsResourceFilterName() {
        return resourceFilterName;
    }

    @Override
    public BootstrapSettings useJqueryPP(boolean useJqueryPP) {
        this.useJqueryPP = useJqueryPP;
        return this;
    }

    @Override
    public boolean isMinified() {
        return Application.get().getResourceSettings().getUseMinifiedResources();
    }

    @Override
    public IBootstrapSettings setJsResourceFilterName(String name) {
        resourceFilterName = name;
        return this;
    }

    @Override
    public IBootstrapSettings setUpdateSecurityManger(boolean activate) {
        updateSecurityManger = activate;
        return this;
    }

    @Override
    public final BootstrapSettings minify(final boolean minify) {
        Application.get().getResourceSettings().setUseMinifiedResources(minify);
        return this;
    }

    @Override
    public boolean useModernizr() {
        return useModernizr;
    }

    @Override
    public BootstrapSettings useModernizr(boolean useModernizr) {
        this.useModernizr = useModernizr;
        return this;
    }

    @Override
    public boolean updateSecurityManger() {
        return updateSecurityManger;
    }

    @Override
    public boolean useResponsiveCss() {
        return useResponsiveCss;
    }

    @Override
    public final BootstrapSettings useResponsiveCss(final boolean useResponsiveCss) {
        this.useResponsiveCss = useResponsiveCss;
        return this;
    }

    @Override
    public ActiveThemeProvider getActiveThemeProvider() {
        return activeThemeProvider;
    }

    @Override
    public BootstrapSettings setActiveThemeProvider(ActiveThemeProvider activeThemeProvider) {
        this.activeThemeProvider = activeThemeProvider;
        return this;
    }

    @Override
    public ThemeProvider getThemeProvider() {
        return themeProvider;
    }

    @Override
    public BootstrapSettings setThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
        return this;
    }

    @Override
    public IBootstrapLessCompilerSettings getBootstrapLessCompilerSettings() {
        return bootstrapLessCompilerSettings;
    }
}
