package de.agilecoders.wicket.core.markup.html.bootstrap.heading;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Heading} and {@code HeadingBehavior}
 *
 * @author miha
 */
@IntegrationTest
class HeadingTest extends WicketApplicationTest {

    @Test
    void isInstantiableWithoutError() {
        Heading heading = new Heading("id");

        for (int i = 1; i <= 6; ++i) {
            tester().startComponentInPage(heading, Markup.of("<h" + i + " wicket:id='id'>Heading</h" + i + ">"));
            tester().assertNoErrorMessage();
        }
    }

    @Test
    void tagNameIsAsserted() {
        Heading heading = new Heading("id");

        assertThrows(MarkupException.class, () ->
            tester().startComponentInPage(heading, Markup.of("<span wicket:id='id'>Heading</span>")));
    }

}
