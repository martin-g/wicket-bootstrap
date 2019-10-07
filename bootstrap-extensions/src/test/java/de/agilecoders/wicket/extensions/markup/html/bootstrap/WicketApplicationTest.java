package de.agilecoders.wicket.extensions.markup.html.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * Base integration test class
 *
 * @author miha
 */
public class WicketApplicationTest extends Assertions {

    private WicketTester tester;

    @BeforeEach
    public final void before() {
        WebApplication application = new WebApplication() {

            @Override
            protected void init() {
                super.init();

                Bootstrap.builder()
                    .withBootstrapSettings(WicketApplicationTest.this.createBootstrapSettings())
                    .install(this);

                getMarkupSettings().setStripWicketTags(false);
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return WicketApplicationTest.this.getHomePage();
            }

        };

        tester = new WicketTester(application);
        onBefore();
    }

    protected void onBefore() {
    }

    private Class<? extends Page> getHomePage() {
        return Page.class;
    }

    @SuppressWarnings("WeakerAccess")
    protected IBootstrapSettings createBootstrapSettings() {
        return new BootstrapSettings();
    }

    protected final WicketTester tester() {
        return tester;
    }

}
