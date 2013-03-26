package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.IDataSource;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.Typeahead;
import de.agilecoders.wicket.core.util.Generics2;

import org.junit.Test;

import java.util.List;

/**
 * Tests the {@link Typeahead} class
 *
 * @author miha
 */
public class TypeaheadTest extends WicketApplicationTest {

    @Test
    public void componentCanBeRendered() {
        tester().startComponentInPage(new Typeahead<String>("typeahead", new IDataSource<String>() {
            @Override
            public List<String> load() {
                return Generics2.newArrayList("a", "b");
            }
        }));

        tester().assertNoErrorMessage();
    }

}
