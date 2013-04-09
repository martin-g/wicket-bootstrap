package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Cite;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.Test;

/**
 * Tests the {@link Cite} class
 *
 * @author miha
 */
public class CiteTest extends WicketApplicationTest {

    @Test(expected = MarkupException.class)
    public void tagNameIsAsserted() {
        startComponentInPage(new Cite(id()));
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(new Cite(id()), Markup.of("<cite wicket:id='id'>Cite</cite>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

}
