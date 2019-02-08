package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Cite} class
 *
 * @author miha
 */
public class CiteTest extends WicketApplicationTest {

    @Test
    public void tagNameIsAsserted() {
        assertThrows(MarkupException.class, () -> startComponentInPage(new Cite(id())));
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(new Cite(id()), Markup.of("<cite wicket:id='id'>Cite</cite>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

}
