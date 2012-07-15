package de.agilecoders.wicket.markup.html.bootstrap.heading;

import de.agilecoders.wicket.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.Test;

/**
 * Tests the {@code Heading} and {@code HeadingBehavior}
 *
 * @author miha
 * @version 1.0
 */
public class HeadingTest extends WicketApplicationTest {

    @Test
    public void isInstantiableWithoutError() {
        Heading heading = new Heading("id");

        for (int i = 1; i <= 6; ++i) {
            tester().startComponentInPage(heading, Markup.of("<h" + i + " wicket:id='id'>Heading</h" + i + ">"));
            tester().assertNoErrorMessage();
        }
    }

    @Test(expected = MarkupException.class)
    public void tagNameIsAsserted() {
        Heading heading = new Heading("id");

        tester().startComponentInPage(heading, Markup.of("<span wicket:id='id'>Heading</span>"));
    }

}
