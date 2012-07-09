package de.agilecoders.wicket;

import de.agilecoders.wicket.settings.BootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;

public class Bootstrap
{
    /**
     * The {@link MetaDataKey} used to retrieve the {@link BootstrapSettings} from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<BootstrapSettings> BOOTSTRAP_SETTINGS_METADATA_KEY = new MetaDataKey<BootstrapSettings>()
    {
    };

    private Bootstrap()
    {
    }

    public static void install(Application app, BootstrapSettings settings)
    {
	app.setMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY, settings);
    }
    
    public static BootstrapSettings getSettings(Application app)
    {
	return app.getMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY);
    }
}
