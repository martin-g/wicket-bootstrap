package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link MetaTag} component
 *
 * @author miha
 */
class MetaTagTest extends WicketApplicationTest {
    static final String MARKUP = "<meta wicket:id=\"id\"/>";

    @Test
    void tagNameIsAsserted() {
        assertThrows(WicketRuntimeException.class, () ->
            startComponentInPage(new MetaTag(id(), Model.of("name"), Model.of(""))));
    }

    @Test
    void nameIsRendered() {
        TagTester tag = startComponentInPage(new MetaTag(id(), Model.of("name-of-meta-tag"), Model.of("")), MARKUP);

        assertThat(tag.getAttribute("name"), is(equalTo("name-of-meta-tag")));
    }

    @Test
    void contentIsRendered() {
        TagTester tag = startComponentInPage(new MetaTag(id(), "name-of-meta-tag", "content-of-meta-tag"), MARKUP);

        assertThat(tag.getAttribute("content"), is(equalTo("content-of-meta-tag")));
    }

    @Test
    void httpEquivNamesAreUsed() {
        TagTester tag = startComponentInPage(new MetaTag(id(), "content-type", "text/html"), MARKUP);

        assertThat(tag.getAttribute("http-equiv"), is(equalTo("content-type")));
    }

    @Test
    void typeSetterWillBeUsed() {
        TagTester tag = startComponentInPage(new MetaTag(id(), "name-of-meta-tag", "text/html").type(MetaTag.Type.HttpEquiv), MARKUP);

        assertThat(tag.getAttribute("http-equiv"), is(equalTo("name-of-meta-tag")));
    }

    /**
     * https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/478
     */
    @Test
    void openGraphProperty() {
        String nameValue = "og:title";
        String content = "The Rock";
        TagTester tag = startComponentInPage(new MetaTag(id(), Model.of(nameValue), Model.of(content)).type(MetaTag.Type.Property), MARKUP);

        assertThat(tag.getAttribute("name"), is(nullValue()));
        assertThat(tag.getAttribute("property"), is(nameValue));
        assertThat(tag.getAttribute("content"), is(equalTo(content)));
    }

    @Test
    void propertyOgNamesAreUsed() {
        String ogTitle = "og:title";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), ogTitle, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(ogTitle)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }

    @Test
    void propertyMusicNamesAreUsed() {
        String property = "music:song";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), property, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(property)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }

    @Test
    void propertyVideoNamesAreUsed() {
        String ogTitle = "video:actor";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), ogTitle, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(ogTitle)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }

    @Test
    void propertyArticleNamesAreUsed() {
        String ogTitle = "article:author";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), ogTitle, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(ogTitle)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }

    @Test
    void propertyBookNamesAreUsed() {
        String ogTitle = "book:author";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), ogTitle, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(ogTitle)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }

    @Test
    void propertyProfileNamesAreUsed() {
        String ogTitle = "profile:first_name";
        String value = "value";
        TagTester tag = startComponentInPage(new MetaTag(id(), ogTitle, value), MARKUP);

        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_PROPERTY), is(equalTo(ogTitle)));
        assertThat(tag.getAttribute(MetaTag.ATTRIBUTE_NAME_CONTENT), is(equalTo(value)));
    }
}
