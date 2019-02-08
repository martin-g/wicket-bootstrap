package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Tests for ButtonBehavior
 */
public class ButtonBehaviorTest extends WicketApplicationTest {

    @Test
    public void fixDisabledButtonMarkup() {
        AbstractLink link = newLink(id());
        link.setEnabled(false);
        link.add(new ButtonBehavior());

        TagTester tagTester = startComponentInPage(link, "<a wicket:id='"+id()+"'>text</a>");
        assertCssClass(tagTester, "btn-disabled");
        String disabledAttr = tagTester.getAttribute("disabled");
        assertEquals("disabled", disabledAttr);
    }

    private AbstractLink newLink(String id) {
        return new Link(id) {
            @Override
            public void onClick() {
            }
        };
    }
}
