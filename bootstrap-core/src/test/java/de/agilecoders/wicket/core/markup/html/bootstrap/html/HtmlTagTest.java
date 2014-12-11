package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.parser.XmlTag;
import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

import java.util.Locale;

public class HtmlTagTest extends WicketApplicationTest {

    private final ComponentTag tag = new ComponentTag("html", XmlTag.TagType.OPEN);

    @Test
    public void onComponentTagDefaultLocale() throws Exception {
        HtmlTag htmlTag = new HtmlTag("id");
        htmlTag.onComponentTag(tag);
        assertThat(tag.getAttribute("lang"), is(equalTo("en")));
    }

    @Test
    public void onComponentTagEnUsLocale() throws Exception {
        HtmlTag htmlTag = new HtmlTag("id", Locale.US);
        htmlTag.onComponentTag(tag);
        assertThat(tag.getAttribute("lang"), is(equalTo("en-us")));
    }
}
