package de.agilecoders.wicket.examples;

import de.agilecoders.wicket.examples.pages.ButtonsPage;
import de.agilecoders.wicket.examples.pages.FormPage;
import de.agilecoders.wicket.examples.pages.HeadingPage;
import de.agilecoders.wicket.examples.pages.HomePage;
import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import org.apache.wicket.settings.IRequestCycleSettings;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends BootstrapApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
        getMarkupSettings().setStripWicketTags(true);

        mountPage("/buttons", ButtonsPage.class);
        mountPage("/heading", HeadingPage.class);
        mountPage("/forms", FormPage.class);
    }
}
