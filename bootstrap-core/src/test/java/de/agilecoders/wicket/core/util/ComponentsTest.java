package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.TestCategory;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.MarkupResourceStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.util.resource.StringResourceStream;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the {@link Components} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class ComponentsTest extends WicketApplicationTest {

    @Test
    public void hasTagNameReturnsFalseIfNullValueIsGiven() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, (String)null), is(equalTo(false)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasTagNameThrowsExceptionIfNullTagIsGiven() {
        Components.hasTagName(null, "div");
    }

    @Test
    public void hasTagNameReturnsTrueIfIsInListOfTagNames() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div", "li", "hr"), is(equalTo(true)));
    }

    @Test
    public void hasTagNameReturnsTrueIfIsSameTagName() {
        final ComponentTag tag = new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div"), is(equalTo(true)));
    }

    @Test
    public void hasTagNameReturnsFalseIfIsNotInListOfTagNames() {
        final ComponentTag tag = new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div", "li", "hr"), is(equalTo(false)));
    }

    @Test
    public void hasTagNameReturnsFalseIfIsNotSameTagName() {
        final ComponentTag tag = new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE);

        assertThat(Components.hasTagName(tag, "div"), is(equalTo(false)));
    }

    @Test
    public void showMakesNothingWithNullValue() {
        Components.show((Component[]) null);
    }

    @Test
    public void assertTagDoesNothingIfTagIsOk() {
        Components.assertTag(createComponentMock(), new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE), "a");
    }

    @Test
    public void assertTagDoesNothingIfTagIsOkWithSet() {
        Components.assertTag(createComponentMock(), new ComponentTag("a", XmlTag.TagType.OPEN_CLOSE), Generics2.newHashSet("a", "li"));
    }

    @Test(expected = MarkupException.class)
    public void assertTagDoesThrowExceptionIfTagIsNotOkWithSet() {
        Components.assertTag(createComponentMock(), new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE), Generics2.newHashSet("a", "li"));
    }

    @Test(expected = MarkupException.class)
    public void assertTagDoesThrowExceptionIfTagIsNotOk() {
        Components.assertTag(createComponentMock(), new ComponentTag("div", XmlTag.TagType.OPEN_CLOSE), "a", "li");
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
