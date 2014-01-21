package de.agilecoders.wicket.core;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.settings.BootstrapResourceAppender;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.webjars.WicketWebjars;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.settings.IMarkupSettings;

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

        if (settings.updateSecurityManager()) {
            updateSecurityManager(app);
        }

        if (settings.autoAppendResources()) {
            app.getComponentInstantiationListeners().add(new BootstrapResourceAppender());
        }

        configureMarkupSettings(app);

        WicketWebjars.install(app);
    }

    /**
     * configure wicket markup settings
     *
     * @param application current application
     */
    private static void configureMarkupSettings(Application application) {
        IMarkupSettings markupSettings = application.getMarkupSettings();

        // wicket markup leads to ui problems because css selectors doesn't match.
        markupSettings.setStripWicketTags(true);
    }

    /**
     * updates the security manager to allow fonts and less files if necessary.
     *
     * @param app The current application
     */
    private static void updateSecurityManager(final Application app) {
        final IPackageResourceGuard packageResourceGuard = app.getResourceSettings().getPackageResourceGuard();

        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard guard = (SecurePackageResourceGuard) packageResourceGuard;
            guard.addPattern("+*.woff");
            guard.addPattern("+*.eot");
            guard.addPattern("+*.svg");
            guard.addPattern("+*.ttf");
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
