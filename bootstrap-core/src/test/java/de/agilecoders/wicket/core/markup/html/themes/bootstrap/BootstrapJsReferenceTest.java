package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;

/**
 * Tests for {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getJsResourceReference()}
 */
class BootstrapJsReferenceTest extends WicketApplicationTest {

    @Test
    void cdnResources() {
        WebApplication application = tester().getApplication();
        application.getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());

        IBootstrapSettings settings = Bootstrap.getSettings(application);
        ResourceReference jsResourceReference = settings.getJsResourceReference();

        CharSequence url = tester().getRequestCycle().urlFor(jsResourceReference, null);
        assertThat(url.toString(), is(equalTo(
                String.format("./wicket/resource/de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference/webjars/bootstrap/%s/js/bootstrap.bundle.js",
                              IBootstrapSettings.VERSION))));

        settings.useCdnResources(true);
        jsResourceReference = settings.getJsResourceReference();
        CharSequence cdnUrl = tester().getRequestCycle().urlFor(jsResourceReference, null);
        assertThat(cdnUrl.toString(), is(equalTo(String.format(IBootstrapSettings.JS_CDN_PATTERN, settings.getVersion()))));
    }

}
