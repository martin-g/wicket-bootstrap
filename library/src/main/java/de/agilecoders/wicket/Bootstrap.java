package de.agilecoders.wicket;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.head.IHeaderResponse;

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
        return getSettings(Application.get());
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
