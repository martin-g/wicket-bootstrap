package de.agilecoders.wicket.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.test.TestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Iterables} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class IterablesTest {

    @Test
    public void transformArrayReturnsAllElementsAsList() {
        final List<String> elements = Iterables.transform(new String[]{ "A", "B" });

        assertThat(elements, is(equalTo((List<String>)Lists.newArrayList("A", "B"))));
    }

    @Test
    public void forEachFiltersAndTransformsArrayOfElements() {
        final List<String> elements = Iterables.forEach(new String[] { "A", "B" }, new Predicate<String>() {
            @Override
            public boolean apply(final String input) {
                return "A".equals(input);
            }
        });

        assertThat(elements, is(equalTo((List<String>)Lists.newArrayList("A"))));
    }

    @Test
    public void forEachFiltersAndTransformsListOfElements() {
        final List<String> elements = Iterables.forEach(Lists.newArrayList("A", "B"), new Predicate<String>() {
            @Override
            public boolean apply(final String input) {
                return "A".equals(input);
            }
        });

        assertThat(elements, is(equalTo((List<String>)Lists.newArrayList("A"))));
    }

}
