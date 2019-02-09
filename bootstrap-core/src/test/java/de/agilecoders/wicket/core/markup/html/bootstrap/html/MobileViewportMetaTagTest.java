package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link MobileViewportMetaTag}
 *
 * @author miha
 */
public class MobileViewportMetaTagTest extends WicketApplicationTest {

    @Test
    public void metaTagIsRenderedWithDeviceWidth() throws Exception {
        MobileViewportMetaTag mobileViewportMetaTag = new MobileViewportMetaTag(id());
        mobileViewportMetaTag.setWidth("device-width");

        TagTester tag = startComponentInPage(mobileViewportMetaTag, MetaTagTest.MARKUP);

        assertThat(tag.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tag.getAttribute("content"), is(equalTo("width=device-width")));
    }

    @Test
    public void metaTagRenderedWithDeviceAndInitialScale() throws Exception {
        MobileViewportMetaTag mobileViewportMetaTag = new MobileViewportMetaTag(id());
        mobileViewportMetaTag.setWidth("device-width");
        mobileViewportMetaTag.setInitialScale("1");

        TagTester tagTester = startComponentInPage(mobileViewportMetaTag, MetaTagTest.MARKUP);

        assertThat(tagTester.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tagTester.getAttribute("content"), is(equalTo("width=device-width,initial-scale=1")));
    }

    @Test
    public void metaTagRenderedWithNoZoom() throws Exception {
        MobileViewportMetaTag mobileViewportMetaTag = new MobileViewportMetaTag(id());
        mobileViewportMetaTag.setWidth("device-width")
                .setInitialScale("1")
                .setMaximumScale("1")
                .setUserScalable(true);

        TagTester tagTester = startComponentInPage(mobileViewportMetaTag, MetaTagTest.MARKUP);

        assertThat(tagTester.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tagTester.getAttribute("content"), is(equalTo("width=device-width,initial-scale=1,maximum-scale=1")));
    }

    @Test
    public void metaTagRenderedWithMinimalScale() throws Exception {
        MobileViewportMetaTag mobileViewportMetaTag = new MobileViewportMetaTag(id());
        mobileViewportMetaTag.setHeight("device-height")
            .setInitialScale("1")
            .setMinimumScale("0.5");

        TagTester tagTester = startComponentInPage(mobileViewportMetaTag, MetaTagTest.MARKUP);

        assertThat(tagTester.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tagTester.getAttribute("content"), is(equalTo("height=device-height,initial-scale=1,minimum-scale=0.5")));
    }

    @Test
    public void metaTagRenderedWithNoScaling() throws Exception {
        MobileViewportMetaTag mobileViewportMetaTag = new MobileViewportMetaTag(id());
        mobileViewportMetaTag.setUserScalable(false);

        TagTester tagTester = startComponentInPage(mobileViewportMetaTag, MetaTagTest.MARKUP);

        assertThat(tagTester.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tagTester.getAttribute("content"), is(equalTo("user-scalable=no")));
    }
}
