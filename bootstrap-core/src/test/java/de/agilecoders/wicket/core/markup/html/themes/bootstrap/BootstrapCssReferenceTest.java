package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the {@link BootstrapCssReference} class.
 *
 * @author miha
 */
public class BootstrapCssReferenceTest extends WicketApplicationTest {
    private String cssContent;

    @Override
    protected void onBefore() {
        try {
            cssContent = IOUtils.toString(BootstrapCssReference.INSTANCE.getResource().getResourceStream().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ResourceStreamNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void lessFileWasGeneratedWithoutError() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        tester().assertNoErrorMessage();
    }

    @Test
    public void lessFileWasGeneratedWithCorrectContent() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        Assert.assertThat(tester().getLastResponseAsString(), is(equalTo(cssContent)));
    }

    @Test
    public void cdnResources() {
        WebApplication application = tester().getApplication();
        application.getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());

        IBootstrapSettings settings = Bootstrap.getSettings(application);
        ResourceReference jsResourceReference = settings.getJsResourceReference();

        CharSequence url = tester().getRequestCycle().urlFor(jsResourceReference, null);
        Assert.assertThat(url.toString(), is(equalTo("./wicket/resource/de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference/webjars/bootstrap/2.3.1/js/bootstrap.js")));

        settings.useCdnResources(true);
        jsResourceReference = settings.getJsResourceReference();
        CharSequence cdnUrl = tester().getRequestCycle().urlFor(jsResourceReference, null);
        Assert.assertThat(cdnUrl.toString(), is(equalTo(String.format(IBootstrapSettings.JS_CDN_PATTERN, settings.getVersion()))));
    }
}
