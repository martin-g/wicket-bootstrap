package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link BootstrapGenericPanel} class
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public class BootstrapGenericPanelTest extends WicketApplicationTest{

    @Test(expected = MarkupException.class)
    public void tagNameIsAsserted() {
        startComponentInPage(new BootstrapGenericPanel<String>(id()));
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(new BootstrapGenericPanel<String>(id()), Markup.of("<div wicket:id='id'>panel</div>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }
	
}