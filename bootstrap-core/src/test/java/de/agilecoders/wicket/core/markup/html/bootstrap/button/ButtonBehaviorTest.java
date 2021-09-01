package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests for ButtonBehavior
 */
class ButtonBehaviorTest extends WicketApplicationTest {

    @Test
    void fixDisabledButtonMarkup() {
        AbstractLink link = newLink(id());
        link.setEnabled(false);
        link.add(new ButtonBehavior());

        TagTester tagTester = startComponentInPage(link, "<a wicket:id='" + id() + "'>text</a>");
        assertCssClass(tagTester, "disabled");
        String disabledAttr = tagTester.getAttribute("disabled");
        assertEquals("disabled", disabledAttr);
    }

    @Test
    void testRenderButtonTypeClass() {
        for (Buttons.Type type : Buttons.Type.values()) {
            AbstractLink link = newLink(id());
            link.add(new ButtonBehavior(type));

            TagTester tag = startComponentInPage(link, "<a wicket:id='" + id() + "'>Link</a>");

            if (type.cssClassName().isEmpty()) {
                assertFalse(tag.hasAttribute("class"));
            } else {
                assertCssClass(tag, type.cssClassName());
            }
        }
    }

    @Test
    void testRenderMenuButtonWithoutBtnClass() {
        AbstractLink link = newLink(id());
        link.add(new ButtonBehavior(Buttons.Type.Menu));

        TagTester tag = startComponentInPage(link, "<a wicket:id='" + id() + "'>Link</a>");

        assertNotContainsCssClass(tag, "btn");
    }

    private AbstractLink newLink(String id) {
        return new Link<Void>(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
            }
        };
    }
}
