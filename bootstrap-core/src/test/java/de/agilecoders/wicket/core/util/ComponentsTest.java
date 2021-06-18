package de.agilecoders.wicket.core.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.MarkupResourceStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.util.resource.StringResourceStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link Components} class
 *
 * @author miha
 */
class ComponentsTest extends WicketApplicationTest {

    @Test
    void hasTagNameReturnsFalseIfNullValueIsGiven() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, (String) null), is(equalTo(false)));
    }

    @Test
    void hasTagNameThrowsExceptionIfNullTagIsGiven() {
        assertThrows(IllegalArgumentException.class, () -> Components.hasTagName(null, "div"));
    }

    @Test
    void hasTagNameReturnsTrueIfIsInListOfTagNames() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div", "li", "hr"), is(equalTo(true)));
    }

    @Test
    void hasTagNameReturnsTrueIfIsSameTagName() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div"), is(equalTo(true)));
    }

    @Test
    void hasTagNameReturnsFalseIfIsNotInListOfTagNames() {
        final ComponentTag tag = new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div", "li", "hr"), is(equalTo(false)));
    }

    @Test
    void hasTagNameReturnsFalseIfIsNotSameTagName() {
        final ComponentTag tag = new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div"), is(equalTo(false)));
    }

    @Test
    void showMakesNothingWithNullValue() {
        Components.show((Component[]) null);
    }

    @Test
    void assertTagDoesNothingIfTagIsOk() {
        Components.assertTag(createComponentMock(), new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE), "a");
    }

    @Test
    void assertTagDoesNothingIfTagIsOkWithSet() {
        Components.assertTag(createComponentMock(), new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE), Set.of("a", "li"));
    }

    @Test
    void assertTagDoesThrowExceptionIfTagIsNotOkWithSet() {
        assertThrows(MarkupException.class, () ->
            Components.assertTag(createComponentMock(), new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE), Set.of("a", "li")));
    }

    @Test
    void assertTagDoesThrowExceptionIfTagIsNotOk() {
        assertThrows(MarkupException.class, () ->
            Components.assertTag(createComponentMock(), new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE), "a", "li"));
    }

    @Test
    void removeCssClassName() throws Exception {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);
        tag.put("class", "class-a class-b class-c");

        Attributes.removeClass(tag, "class-a", "class-c");

        assertThat(tag.getAttribute("class"), is("class-b"));
    }

    @Test
    void removeCssClassNameWithEmptyClassAttribute() throws Exception {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);
        tag.put("class", "");
        Attributes.removeClass(tag, "class-a", "class-c");

        tag.remove("class");
        Attributes.removeClass(tag, "class-a", "class-c");
    }

    /**
     * @return new component mock
     */
    private Component createComponentMock() {
        Component component = mock(WebMarkupContainer.class, Mockito.RETURNS_SMART_NULLS);

        when(component.getId()).thenReturn("id");
        when(component.getPath()).thenReturn("path");

        IMarkupFragment markupFragment = mock(IMarkupFragment.class);
        when(markupFragment.getMarkupResourceStream()).thenReturn(
                new MarkupResourceStream(new StringResourceStream("markup")));
        when(component.getMarkup()).thenReturn(markupFragment);

        return component;
    }

}
