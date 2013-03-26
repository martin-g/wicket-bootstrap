package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CiteBehavior;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.Test;

/**
 * Tests the {@link CiteBehavior} class
 *
 * @author miha
 */
public class CiteBehaviorTest extends WicketApplicationTest {

    @Test(expected = MarkupException.class)
    public void tagNameIsAsserted() {
        startBehaviorInPage(new CiteBehavior());
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CiteBehavior()), Markup.of("<cite wicket:id='id'>Cite</cite>"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

}
