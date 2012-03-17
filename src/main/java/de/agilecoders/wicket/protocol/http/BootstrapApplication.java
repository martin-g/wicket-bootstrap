package de.agilecoders.wicket.protocol.http;

import com.google.common.base.Charsets;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public abstract class BootstrapApplication extends WebApplication implements IBootstrapApplication {

    private IBootstrapSettings bootstrapSettings;

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        getMarkupSettings().setDefaultMarkupEncoding(Charsets.UTF_8.name());
        getRequestCycleSettings().setResponseRequestEncoding(Charsets.UTF_8.name());
    }

    /**
     * @return bootstrap related settings
     */
    public IBootstrapSettings getBootstrapSettings() {
        if (bootstrapSettings == null) {
            bootstrapSettings = new BootstrapSettings();
        }

        return bootstrapSettings;
    }

    /**
     * @param bootstrapSettings bootstrap related settings
     */
    public final void setBootstrapSettings(final IBootstrapSettings bootstrapSettings) {
        this.bootstrapSettings = bootstrapSettings;
    }
}
