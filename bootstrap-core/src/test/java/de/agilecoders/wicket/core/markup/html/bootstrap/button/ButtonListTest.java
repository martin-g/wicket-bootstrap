package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;

/**
 * Tests for ButtonList
 */
public class ButtonListTest extends WicketApplicationTest {

    /**
     * Issue https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/316
     */
    @Test
    public void hasNoActiveButton() {

        TestPage page = new TestPage();
        NavbarButton<?> buttonUnderTest = new NavbarButton<>(TestPage.class, Model.of("Button"));
        page.add(buttonUnderTest);

        List<AbstractLink> buttons = new ArrayList<AbstractLink>();
        DropDownButton dropDownButton1 = new DropDownButton("dropDown", Model.of("")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                return new ArrayList<AbstractLink>();
            }
        };
        buttons.add(dropDownButton1);
        ButtonList buttonList = new ButtonList("id", buttons);

        assertFalse(buttonList.hasActiveButton(buttonUnderTest));
    }

    /**
     * Issue https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/316
     */
    @Test
    public void hasActiveButton() {

        TestPage page = new TestPage();
        final NavbarButton<?> buttonUnderTest = new NavbarButton<>(TestPage.class, Model.of("Button"));
        page.add(buttonUnderTest);

        List<AbstractLink> buttons = new ArrayList<AbstractLink>();
        DropDownButton dropDownButton1 = new DropDownButton("dropDown", Model.of("")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                return Collections.<AbstractLink>singletonList(buttonUnderTest);
            }
        };
        buttons.add(dropDownButton1);
        ButtonList buttonList = new ButtonList("id", buttons);

        assertTrue(buttonList.hasActiveButton(buttonUnderTest));
    }

    private static class TestPage extends WebPage {
    }
}
