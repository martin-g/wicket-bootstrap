package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link AssertTagNameBehavior} class
 *
 * @author miha
 */
@IntegrationTest
class AssertTagNameBehaviorTest extends WicketApplicationTest {

    @Test
    void tagNameIsAsserted() {
        final Component component = new TestDivContainerComponent();
        component.add(new AssertTagNameBehavior("li"));

        assertThrows(MarkupException.class, () -> tester().startComponentInPage(component));
    }

    @Test
    void tagNameIsAssertedButNoExceptionWillBeThrownForCorrectTagName() {
        final Component component = new TestDivContainerComponent();
        component.add(new AssertTagNameBehavior("div", "ul"));

        tester().startComponentInPage(component);
    }

    /**
     * Helper component to test {@link AssertTagNameBehavior}
     */
    private static final class TestDivContainerComponent extends WebMarkupContainer {

        /**
         * Construct.
         */
        TestDivContainerComponent() {
            super("container");
        }

        @Override
        public Markup getAssociatedMarkup() {
            return Markup.of("<div wicket:id=\"container\"></div>");
        }

        @Override
        protected void onComponentTag(ComponentTag tag) {
            tag.setName("div");

            super.onComponentTag(tag);
        }
    }
}
