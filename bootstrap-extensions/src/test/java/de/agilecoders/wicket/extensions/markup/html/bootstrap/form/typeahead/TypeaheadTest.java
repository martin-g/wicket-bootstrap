package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Typeahead} class
 *
 * @author miha
 */
public class TypeaheadTest extends WicketApplicationTest {

    @Test
    public void componentCanBeRendered() {
        tester().startComponentInPage(new Typeahead<String>("typeahead", new Dataset("test")),
            Markup.of("<html><head></head><body><input type='text' wicket:id='typeahead'/></body></html>"));

        tester().assertNoErrorMessage();
    }

}
