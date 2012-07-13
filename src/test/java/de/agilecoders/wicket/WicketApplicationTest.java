package de.agilecoders.wicket;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import de.agilecoders.wicket.protocol.http.IBootstrapApplication;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class WicketApplicationTest {

    private WebApplication application;
    private WicketTester tester;

    @Before
    public final void before() {
        application = new BootstrapApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return Page.class;
            }
        };
        ((IBootstrapApplication) application).setBootstrapSettings(createBootstrapSettings());

        tester = new WicketTester(application);
    }

    protected IBootstrapSettings createBootstrapSettings() {
        return new BootstrapSettings();
    }

    protected final WicketTester tester() {
        return tester;
    }

    protected final IBootstrapApplication application() {
        return (IBootstrapApplication) application;
    }

}
