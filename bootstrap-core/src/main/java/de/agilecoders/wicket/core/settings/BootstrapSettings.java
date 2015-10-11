package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.JQueryPluginUrlResourceReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * #### Description
 *
 * Default {@link IBootstrapSettings} implementation
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 * @see IBootstrapSettings
 */
public class BootstrapSettings implements IBootstrapSettings {

    private static final class Holder {
        private static ResourceReference bootstrapJavaScriptReference = BootstrapJavaScriptReference.instance();
        private static ResourceReference modernizrJavaScriptReference = ModernizrJavaScriptReference.instance();
        private static ResourceReference bootstrapCssReference = BootstrapCssReference.instance();
    }

    private ResourceReference bootstrapJavaScriptReference = null;
    private ResourceReference modernizrJavaScriptReference = null;
    private ResourceReference bootstrapCssReference = null;

    private ThemeProvider themeProvider;
    private ActiveThemeProvider activeThemeProvider;
    private String resourceFilterName;
    private boolean updateSecurityManager;
    private boolean autoAppendResources;
    private boolean useCdnResources;

    private boolean deferJavascript;
    private String version = VERSION;
    private String modernizrVersion = MODERNIZR_VERSION;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        this.activeThemeProvider = new SessionThemeProvider();
        this.themeProvider = new NoopThemeProvider();
        this.resourceFilterName = "";
        this.updateSecurityManager = true;
        this.autoAppendResources = true;
        this.useCdnResources = false;
        this.deferJavascript = false;
    }

    @Override
    public IBootstrapSettings setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public IBootstrapSettings setModernizrVersion(String version) {
        this.modernizrVersion = version;
        return this;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public boolean deferJavascript() {
        return deferJavascript;
    }

    @Override
    public String getModernizrVersion() {
        return modernizrVersion;
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
            jsReference = new JQueryPluginUrlResourceReference(Url.parse(cdnUrl));
        } else {
            jsReference = bootstrapJavaScriptReference;
        }

        return jsReference != null ? jsReference : Holder.bootstrapJavaScriptReference;
    }

    @Override
    public ResourceReference getModernizrResourceReference() {
        ResourceReference jsReference;

        if (useCdnResources()) {
            String cdnUrl = String.format(MODERNIZR_CDN_PATTERN, getModernizrVersion());
            jsReference = new UrlResourceReference(Url.parse(cdnUrl));
        } else {
            jsReference = modernizrJavaScriptReference;
        }

        return jsReference != null ? jsReference : Holder.modernizrJavaScriptReference;
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

        return provider != null ? provider : (themeProvider = new DefaultThemeProvider());
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
    public BootstrapSettings setModernizrResourceReference(final ResourceReference modernizrJavaScriptReference) {
        this.modernizrJavaScriptReference = modernizrJavaScriptReference;
        return this;
    }

    @Override
    public IBootstrapSettings setUpdateSecurityManager(boolean activate) {
        updateSecurityManager = activate;
        return this;
    }

    @Override
    public IBootstrapSettings setDeferJavascript(boolean defer) {
        deferJavascript = defer;
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
    public final boolean useWebjars() {
        return !useCdnResources() &&
            (bootstrapCssReference == null ||
                bootstrapJavaScriptReference == null ||
                modernizrJavaScriptReference == null);
    }

    @Override
    public IBootstrapSettings useCdnResources(boolean useCdnResources) {
        this.useCdnResources = useCdnResources;
        return this;
    }
}
