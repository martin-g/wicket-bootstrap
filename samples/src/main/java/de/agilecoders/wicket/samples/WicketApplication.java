package de.agilecoders.wicket.samples;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.samples.pages.HomePage;
import de.agilecoders.wicket.settings.BootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import java.io.IOException;
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

        // Allow fonts.
        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
            guard.addPattern("+*.woff");
            guard.addPattern("+*.ttf");
            guard.addPattern("+*.svg");
        }

        configureBootstrap();

        new AnnotatedMountScanner().scanPackage("de.agilecoders.wicket.samples.pages").mount(this);
    }

    private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.minify(true); // use minimized version of all bootstrap references
        settings.useJqueryPP(true);
        settings.useModernizr(true);
        settings.useResponsiveCss(true);
        settings.getBootstrapLessCompilerSettings().setUseLessCompiler(true);

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
