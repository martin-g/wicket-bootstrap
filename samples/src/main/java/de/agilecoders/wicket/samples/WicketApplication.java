package de.agilecoders.wicket.samples;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.metro.MetroTheme;
import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
import de.agilecoders.wicket.samples.pages.HomePage;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.BootswatchThemeProvider;
import de.agilecoders.wicket.settings.ThemeProvider;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.head.filter.AbstractHeaderResponseFilter;
import org.apache.wicket.markup.head.filter.FilteringHeaderResponse;
import org.apache.wicket.markup.head.filter.OppositeHeaderResponseFilter;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Demo Application instance.
 */
public class WicketApplication extends WebApplication {
    private Properties properties;

    /**
     * Get Application for current thread.
     *
     * @return The current thread's Application
     */
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }

    /**
     * Constructor.
     */
    public WicketApplication() {
        super();

        properties = loadProperties();
        setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // wicket markup leads to strange ui problems because css selectors
        // won't match anymore.
        getMarkupSettings().setStripWicketTags(true);

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        // Allow fonts.
        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
            guard.addPattern("+*.woff");
            guard.addPattern("+*.ttf");
            guard.addPattern("+*.svg");
        }

        getResourceSettings().setDefaultCacheDuration(Duration.milliseconds(0));
        getResourceSettings().setResourcePollFrequency(Duration.seconds(2));
        getResourceSettings().setCachingStrategy(NoOpResourceCachingStrategy.INSTANCE);

        configureBootstrap();
        configureResourceBundles();

        new AnnotatedMountScanner().scanPackage("de.agilecoders.wicket.samples.pages").mount(this);
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {
        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "core.js",
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getJQueryReference(),
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketEventReference(),
                                                 (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketAjaxReference(),
                                                 (JavaScriptResourceReference) ModernizrJavaScriptReference.INSTANCE
        );

        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap.js",
                                                 (JavaScriptResourceReference) Bootstrap.getSettings().getJsResourceReference(),
                                                 (JavaScriptResourceReference) Bootstrap.getSettings().getJqueryPPResourceReference(),
                                                 (JavaScriptResourceReference) BootstrapPrettifyJavaScriptReference.INSTANCE,
                                                 ApplicationJavaScript.INSTANCE
        );

        getResourceBundles().addCssBundle(WicketApplication.class, "application.css",
                                          (CssResourceReference) Bootstrap.getSettings().getResponsiveCssResourceReference(),
                                          (CssResourceReference) BootstrapPrettifyCssReference.INSTANCE,
                                          FixBootstrapStylesCssResourceReference.INSTANCE
        );

        setHeaderResponseDecorator(new IHeaderResponseDecorator() {
            public IHeaderResponse decorate(final IHeaderResponse response) {
                final String jsFooterBucket = "footer-container";
                final List<FilteringHeaderResponse.IHeaderResponseFilter> filters = new ArrayList<FilteringHeaderResponse.IHeaderResponseFilter>();
                final AbstractHeaderResponseFilter jsAcceptingFilter = new AbstractHeaderResponseFilter(jsFooterBucket) {
                    public boolean accepts(HeaderItem item) {
                        return item instanceof JavaScriptHeaderItem ||
                               item instanceof OnDomReadyHeaderItem ||
                               item instanceof OnLoadHeaderItem;
                    }
                };

                filters.add(jsAcceptingFilter);
                OppositeHeaderResponseFilter nonJsFilter = new OppositeHeaderResponseFilter("headBucket", jsAcceptingFilter);
                filters.add(nonJsFilter);

                return new FilteringHeaderResponse(response, "headBucket", filters);
            }
        });
    }

    private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.minify(true) // use minimized version of all bootstrap references
                .useJqueryPP(true)
                .useModernizr(true)
                .useResponsiveCss(true)
                .setJsResourceFilterName("footer-container")
                .getBootstrapLessCompilerSettings().setUseLessCompiler(false);

        ThemeProvider themeProvider = new BootswatchThemeProvider() {{
            add(new MetroTheme());
            defaultTheme("wicket");
        }};
        settings.setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
    }

    public Properties getProperties() {
        return properties;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
