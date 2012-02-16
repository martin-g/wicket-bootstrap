package de.agilecoders.wicket.protocol.http;

import de.agilecoders.wicket.io.Encoding;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IMarkupSettings;
import org.apache.wicket.settings.def.MarkupSettings;

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

        getMarkupSettings().setDefaultMarkupEncoding(Encoding.UTF8);
        getRequestCycleSettings().setResponseRequestEncoding(Encoding.UTF8);
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
