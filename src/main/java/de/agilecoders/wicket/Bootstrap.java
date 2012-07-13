package de.agilecoders.wicket;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;

public class Bootstrap {
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
}
