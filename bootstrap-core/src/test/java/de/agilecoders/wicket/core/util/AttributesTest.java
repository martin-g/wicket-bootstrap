package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.parser.XmlTag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Tests the {@link Attributes} class
 *
 * @author miha
 */
class AttributesTest extends WicketApplicationTest {

    @Test
    void singleClassNameIsAddedToMarkup() {
        startComponentInPage(createComponentWithCssClassNames("classname"));

        assertClassNamesPresent("classname");
    }

    @Test
    void multipleClassNameIsAddedToMarkup() {
        startComponentInPage(createComponentWithCssClassNames("classname-a", "classname-b"));

        assertClassNamesPresent("classname-a", "classname-b");
    }

    @Test
    void emptyClassNameIsIgnored() {
        startComponentInPage(createComponentWithCssClassNames(""));

        assertThat(tester().getTagByWicketId(id()).getAttribute("class"), is(nullValue()));
    }

    @Test
    void nullClassNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> startComponentInPage(createComponentWithCssClassNames((String[]) null)));
    }

    @Test
    void removeClass() {
        ComponentTag tag = new ComponentTag("span", XmlTag.TagType.OPEN_CLOSE);
        tag.put("class", "class1 float-end class2");

        Attributes.removeClass(tag, "float-end");

        assertThat(tag.getAttribute("class"), is(equalTo("class1 class2")));
    }

    /**
     * creates a new component with an onComponentTag method that adds
     * given class names.
     *
     * @param classNames class names to add
     * @return new component
     */
    private Component createComponentWithCssClassNames(final String... classNames) {
        WebMarkupContainer component = new WebMarkupContainer(id()) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Attributes.addClass(tag, classNames);
            }
        };
        component.setOutputMarkupId(true);
        return component;
    }

}
