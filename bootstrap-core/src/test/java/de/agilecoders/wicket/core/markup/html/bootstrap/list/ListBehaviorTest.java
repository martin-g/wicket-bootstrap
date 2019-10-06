package de.agilecoders.wicket.core.markup.html.bootstrap.list;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link ListBehavior}
 *
 * @author miha
 */
class ListBehaviorTest extends WicketApplicationTest {

    @Test
    void orderedList() {
        TagTester tag = startBehaviorInPage(ListBehavior.ordered());

        assertThat(tag.getName(), is(equalTo("ol")));
    }

    @Test
    void unorderedList() {
        TagTester tag = startBehaviorInPage(ListBehavior.unordered());

        assertThat(tag.getName(), is(equalTo("ul")));
    }

    @Test
    void descriptionList() {
        TagTester tag = startBehaviorInPage(ListBehavior.description());

        assertThat(tag.getName(), is(equalTo("dl")));
    }

    @Test
    void horizontalDescriptionList() {
        startBehaviorInPage(ListBehavior.description().horizontal());

        assertClassNamesPresent("row");
    }

    @Test
    void unstyledUnorderedList() {
        startBehaviorInPage(ListBehavior.unordered().unstyled());

        assertClassNamesPresent("list-unstyled");
    }

    @Test
    void inlineUnorderedList() {
        startBehaviorInPage(ListBehavior.unordered().inline());

        assertClassNamesPresent("list-inline");
    }
}
