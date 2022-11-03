package de.agilecoders.wicket.samples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.settings.RequestCycleSettings;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.CookieThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteFileStorage;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteStoredImageResourceReference;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.samples.pages.HomePage;
import de.agilecoders.wicket.sass.BootstrapSass;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import net.ftlines.wicketsource.WicketSource;

/**
 * Demo Application instance.
 */
public class WicketApplication extends WebApplication {

    /**
     * The id of the storage for images
     */
    public static final String STORAGE_ID = "fileStorage";


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

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();

        getCspSettings().blocking().disabled();
        getApplicationSettings().setUploadProgressUpdatesEnabled(true);

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        configureBootstrap();
//        configureResourceBundles();

        optimizeForWebPerformance();

        new AnnotatedMountScanner().scanPackage("de.agilecoders.wicket.samples.pages").mount(this);

        if (Strings.isTrue(properties.getProperty("cdn.useCdn"))) {
            final String cdn = properties.getProperty("cdn.baseUrl");

            StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
        }

        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            securePackageResourceGuard.addPattern("+*.woff2");
        }

        configureSummernote();

        FontAwesomeSettings.get(Application.get()).setCssResourceReference(FontAwesome6CssReference.instance());

        WicketSource.configure(this);
    }

    /**
     * Setups Summernote's file storage for image uploads
     */
    private void configureSummernote() {
        // the folder where to store the images
        Folder folder = new Folder(System.getProperty("java.io.tmpdir"), "bootstrap-summernote");
        folder.mkdirs();
        folder.deleteOnExit();

        SummernoteConfig.addStorage(new SummernoteFileStorage(STORAGE_ID, folder));

        // mount the resource reference responsible for image uploads
        mountResource(SummernoteStoredImageResourceReference.SUMMERNOTE_MOUNT_PATH,
                      new SummernoteStoredImageResourceReference(STORAGE_ID));
    }

    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(
                    "-v-",
                    new CachingResourceVersion(new Adler32ResourceVersion())
            ));

            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        } else {
            getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
        }

        getHeaderResponseDecorators().add(response -> new JavaScriptFilteredIntoFooterHeaderResponse(response, "scripts"));
        getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
        final IBootstrapSettings settings = new BootstrapSettings();
        Bootstrap.builder().withBootstrapSettings(settings).install(this);
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Flatly);

        settings.setJsResourceFilterName("footer-container")
                .setThemeProvider(themeProvider)
                .setActiveThemeProvider(new CookieThemeProvider());

        BootstrapSass.install(this);
    }

    /**
     * @return used configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * loads all configuration properties from disk
     *
     * @return configuration properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream stream = getClass().getResourceAsStream("/config.properties");
            try {
                properties.load(stream);
            } finally {
                IOUtils.closeQuietly(stream);
            }
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return properties;
    }
}
