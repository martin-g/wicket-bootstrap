package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * Default {@link IBootstrapSettings} implementation
 *
 * @author miha
 * @see IBootstrapSettings
 */
public class BootstrapSettings implements IBootstrapSettings {

    private static final class Holder {
        private static ResourceReference bootstrapJavaScriptReference = BootstrapJavaScriptReference.instance();
        private static ResourceReference bootstrapCssReference = BootstrapCssReference.instance();
        private static ThemeProvider themeProvider = new DefaultThemeProvider();
    }

    private ResourceReference bootstrapJavaScriptReference = null;
    private ResourceReference bootstrapCssReference = null;

    private ThemeProvider themeProvider = null;
    private ActiveThemeProvider activeThemeProvider = new SessionThemeProvider();
    private String resourceFilterName = "";
    private boolean updateSecurityManager = true;
    private boolean autoAppendResources = true;
    private boolean useCdnResources = false;
    private String version = VERSION;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        // so far nothing to do here
    }

    @Override
    public IBootstrapSettings setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public boolean autoAppendResources() {
        return autoAppendResources;
    }

    @Override
    public ResourceReference getCssResourceReference() {
        ResourceReference ref;

        if (useCdnResources()) {
            String cdnUrl = String.format(CSS_CDN_PATTERN, getVersion());
            ref = new UrlResourceReference(Url.parse(cdnUrl));
        } else {
            ref = bootstrapCssReference;
        }

        return ref != null ? ref : Holder.bootstrapCssReference;
    }

    @Override
    public ResourceReference getJsResourceReference() {
        ResourceReference jsReference;

        if (useCdnResources()) {
            String cdnUrl = String.format(JS_CDN_PATTERN, getVersion());
            jsReference = new UrlResourceReference(Url.parse(cdnUrl));
        } else {
            jsReference = bootstrapJavaScriptReference;
        }

        return jsReference != null ? jsReference : Holder.bootstrapJavaScriptReference;
    }

    @Override
    public String getJsResourceFilterName() {
        return resourceFilterName;
    }

    @Override
    public IBootstrapSettings setAutoAppendResources(boolean value) {
        this.autoAppendResources = value;
        return this;
    }

    @Override
    public ActiveThemeProvider getActiveThemeProvider() {
        return activeThemeProvider;
    }

    @Override
    public ThemeProvider getThemeProvider() {
        ThemeProvider provider = themeProvider;

        return provider != null ? provider : Holder.themeProvider;
    }

    @Override
    public boolean updateSecurityManager() {
        return updateSecurityManager;
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
    public IBootstrapSettings setUpdateSecurityManager(boolean activate) {
        updateSecurityManager = activate;
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

    @Override
    public boolean useCdnResources() {
        return useCdnResources;
    }

    @Override
    public IBootstrapSettings useCdnResources(boolean useCdnResources) {
        this.useCdnResources = useCdnResources;
        return this;
    }
}
