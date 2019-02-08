package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link BootstrapGenericPanel} class
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public class BootstrapGenericPanelTest extends WicketApplicationTest{

    @Test
    void tagNameIsAsserted() {
        assertThrows(MarkupException.class, () -> startComponentInPage(new BootstrapGenericPanel<String>(id())));
    }

    @Test
    void isRenderedWithoutException() {
        tester().startComponentInPage(new BootstrapGenericPanel<String>(id()), Markup.of("<div wicket:id='id'>panel</div>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }
	
}
