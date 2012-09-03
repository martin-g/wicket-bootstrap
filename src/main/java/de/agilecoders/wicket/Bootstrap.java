package de.agilecoders.wicket;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Bootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * The {@link MetaDataKey} used to retrieve the {@link IBootstrapSettings} from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<IBootstrapSettings> BOOTSTRAP_SETTINGS_METADATA_KEY = new MetaDataKey<IBootstrapSettings>() {
    };

    private Bootstrap() {
    }

    public static void install(Application app, IBootstrapSettings settings) {
        app.setMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY, settings);
    }

    public static IBootstrapSettings getSettings(Application app) {
        return app.getMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY);
    }

    public static IBootstrapSettings getSettings() {
        return getSettings(Application.get());
    }

    public static void renderHead(Component component, IHeaderResponse response) {
        new BootstrapResourcesBehavior().renderHead(getSettings(component.getApplication()), response);
    }

    public static void renderHead(IHeaderResponse response) {
        new BootstrapResourcesBehavior().renderHead(getSettings(Application.get()), response);
    }
}
