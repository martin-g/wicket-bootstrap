package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Tests the {@link Attributes} class
 *
 * @author miha
 */
public class AttributesTest extends WicketApplicationTest {

    @Test
    public void singleClassNameIsAddedToMarkup() {
        startComponentInPage(createComponentWithCssClassNames("classname"));

        assertClassNamesPresent("classname");
    }

    @Test
    public void multipleClassNameIsAddedToMarkup() {
        startComponentInPage(createComponentWithCssClassNames("classname-a", "classname-b"));

        assertClassNamesPresent("classname-a", "classname-b");
    }

    @Test
    public void emptyClassNameIsIgnored() {
        startComponentInPage(createComponentWithCssClassNames(""));

        assertThat(tester().getTagByWicketId(id()).getAttribute("class"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullClassNameThrowsException() {
        startComponentInPage(createComponentWithCssClassNames(null));
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
