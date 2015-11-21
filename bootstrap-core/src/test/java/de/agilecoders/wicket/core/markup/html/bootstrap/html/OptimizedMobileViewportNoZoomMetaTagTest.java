package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the {@link OptimizedMobileViewportMetaTag}
 *
 * @author miha
 */
public class OptimizedMobileViewportNoZoomMetaTagTest extends WicketApplicationTest {

    @Test
    public void metaTagIsRendered() throws Exception {
        TagTester tag = startComponentInPage(new OptimizedMobileViewportMetaTag(id()), MetaTagTest.MARKUP);

        assertThat(tag.getAttribute("name"), is(equalTo("viewport")));
        assertThat(tag.getAttribute("content"), is(equalTo("width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no")));
    }
}
