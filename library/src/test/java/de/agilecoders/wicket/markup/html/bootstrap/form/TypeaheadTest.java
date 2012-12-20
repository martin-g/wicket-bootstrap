package de.agilecoders.wicket.markup.html.bootstrap.form;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.WicketApplicationTest;
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
                return Lists.newArrayList("a", "b");
            }
        }));

        tester().assertNoErrorMessage();
    }

}
