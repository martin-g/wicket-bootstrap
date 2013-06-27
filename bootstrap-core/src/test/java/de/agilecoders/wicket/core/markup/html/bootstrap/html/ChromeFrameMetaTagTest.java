package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link ChromeFrameMetaTag}
 *
 * @author miha
 */
public class ChromeFrameMetaTagTest extends WicketApplicationTest {

    @Test
    public void metaTagIsRendered() throws Exception {
        TagTester tag = startComponentInPage(new ChromeFrameMetaTag(id()), MetaTagTest.MARKUP);

        assertThat(tag.getAttribute("name"), is(equalTo("X-UA-Compatible")));
        assertThat(tag.getAttribute("content"), is(equalTo("IE=edge,chrome=1")));
    }
}
