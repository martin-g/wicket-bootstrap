package de.agilecoders.wicket.core.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.Test;

/**
 * Tests the {@link Typeahead} class
 *
 * @author miha
 */
public class TypeaheadTest extends WicketApplicationTest {

    @Test
    public void componentCanBeRendered() {
        tester().startComponentInPage(new Typeahead<String>("typeahead", new Dataset("test")));

        tester().assertNoErrorMessage();
    }

}
