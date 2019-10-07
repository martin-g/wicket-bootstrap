package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.parser.XmlTag;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class HtmlTagTest extends WicketApplicationTest {

    private final ComponentTag tag = new ComponentTag("html", XmlTag.TagType.OPEN);

    @Test
    void onComponentTagDefaultLocale() {
        HtmlTag htmlTag = new HtmlTag("id");
        htmlTag.onComponentTag(tag);
        assertThat(tag.getAttribute("lang"), is(equalTo("en")));
    }

    @Test
    void onComponentTagEnUsLocale() {
        HtmlTag htmlTag = new HtmlTag("id", Locale.US);
        htmlTag.onComponentTag(tag);
        assertThat(tag.getAttribute("lang"), is(equalTo("en-US")));
    }
}
