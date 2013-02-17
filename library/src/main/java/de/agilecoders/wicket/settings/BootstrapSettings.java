package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Default {@link IBootstrapSettings} implementation
 *
 * @author miha
 * @see IBootstrapSettings
 */
public class BootstrapSettings implements IBootstrapSettings {

    private ResourceReference bootstrapJavaScriptReference = BootstrapJavaScriptReference.instance();
    private ResourceReference bootstrapCssReference = BootstrapResponsiveCssReference.INSTANCE;

    private ThemeProvider themeProvider = new DefaultThemeProvider();
    private ActiveThemeProvider activeThemeProvider = new SessionThemeProvider();
    private String resourceFilterName = "";
    private boolean updateSecurityManger = true;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        // so far nothing to do here
    }

    @Override
    public ResourceReference getCssResourceReference() {
        return bootstrapCssReference;
    }

    @Override
    public ResourceReference getJsResourceReference() {
        return bootstrapJavaScriptReference;
    }

    @Override
    public String getJsResourceFilterName() {
        return resourceFilterName;
    }

    @Override
    public ActiveThemeProvider getActiveThemeProvider() {
        return activeThemeProvider;
    }

    @Override
    public ThemeProvider getThemeProvider() {
        return themeProvider;
    }

    @Override
    public boolean updateSecurityManger() {
        return updateSecurityManger;
    }

    @Override
    public IBootstrapSettings setCssResourceReference(ResourceReference reference) {
        bootstrapCssReference = reference;
        return this;
    }

    @Override
    public BootstrapSettings setJsResourceReference(final ResourceReference bootstrapJavaScriptReference) {
        this.bootstrapJavaScriptReference = bootstrapJavaScriptReference;
        return this;
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
    public BootstrapSettings setActiveThemeProvider(ActiveThemeProvider activeThemeProvider) {
        this.activeThemeProvider = activeThemeProvider;
        return this;
    }

    @Override
    public BootstrapSettings setThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
        return this;
    }
}
