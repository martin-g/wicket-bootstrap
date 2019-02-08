package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link CiteBehavior} class
 *
 * @author miha
 */
public class CiteBehaviorTest extends WicketApplicationTest {

    @Test
    public void tagNameIsAsserted()
    {
        assertThrows(MarkupException.class, () -> startBehaviorInPage(new CiteBehavior()));
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CiteBehavior()), Markup.of("<cite wicket:id='id'>Cite</cite>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

}
