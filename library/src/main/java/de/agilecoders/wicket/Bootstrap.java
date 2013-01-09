package de.agilecoders.wicket;

import de.agilecoders.wicket.less.LessResourceStreamLocator;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.core.util.resource.locator.caching.CachingResourceStreamLocator;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;

/**
 * Bootstrap core class
 */
public final class Bootstrap {

    /**
     * The {@link MetaDataKey} used to retrieve the {@link IBootstrapSettings} from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<IBootstrapSettings> BOOTSTRAP_SETTINGS_METADATA_KEY = new MetaDataKey<IBootstrapSettings>() {
    };

    /**
     * Construct.
     */
    private Bootstrap() {
        throw new UnsupportedOperationException();
    }

    /**
     * Installs given settings for given application
     *
     * @param app      The current application
     * @param settings The settings to use
     */
    public static void install(final Application app, final IBootstrapSettings settings) {
        app.setMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY, settings);

        if (settings.getBootstrapLessCompilerSettings().useLessCompiler()) {
            app.getResourceSettings().setResourceStreamLocator(new CachingResourceStreamLocator(new LessResourceStreamLocator()));
        }

        if (settings.updateSecurityManger()) {
            updateSecurityManager(app, settings);
        }
    }

    /**
     * updates the security manager to allow fonts and less files if necessary.
     *
     * @param app      The current application
     * @param settings The settings to use
     */
    private static void updateSecurityManager(final Application app, final IBootstrapSettings settings) {
        final IPackageResourceGuard packageResourceGuard = app.getResourceSettings().getPackageResourceGuard();

        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
            guard.addPattern("+*.woff");
            guard.addPattern("+*.eot");
            guard.addPattern("+*.svg");
            guard.addPattern("+*.ttf");

            if (settings.getBootstrapLessCompilerSettings().useLessCompiler()) {
                guard.addPattern("+*.less");
            }
        }
    }

    /**
     * returns the {@link IBootstrapSettings} which are assigned to given application
     *
     * @param app The current application
     * @return assigned {@link IBootstrapSettings}
     */
    public static IBootstrapSettings getSettings(final Application app) {
        return app.getMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY);
    }

    /**
     * returns the {@link IBootstrapSettings} which are assigned to current application
     *
     * @return assigned {@link IBootstrapSettings}
     */
    public static IBootstrapSettings getSettings() {
        if (Application.exists()) {
            return getSettings(Application.get());
        }

        throw new IllegalStateException("there is no active application assigned to this thread.");
    }

    /**
     * renders all core bootstrap resource references
     *
     * @param component current component
     * @param response  The current {@link IHeaderResponse}
     */
    public static void renderHead(final Component component, final IHeaderResponse response) {
        BootstrapResourcesBehavior.instance().renderHead(getSettings(component.getApplication()), response);
    }

    /**
     * renders all core bootstrap resource references
     *
     * @param response The current {@link IHeaderResponse}
     */
    public static void renderHead(final IHeaderResponse response) {
        BootstrapResourcesBehavior.instance().renderHead(getSettings(Application.get()), response);
    }
}
