package de.agilecoders.wicket.core.markup.html.bootstrap.list;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link ListBehavior}
 *
 * @author miha
 */
public class ListBehaviorTest extends WicketApplicationTest {

    @Test
    public void orderedList() {
        TagTester tag = startBehaviorInPage(ListBehavior.ordered());

        assertThat(tag.getName(), is(equalTo("ol")));
    }

    @Test
    public void unorderedList() {
        TagTester tag = startBehaviorInPage(ListBehavior.unordered());

        assertThat(tag.getName(), is(equalTo("ul")));
    }

    @Test
    public void descriptionList() {
        TagTester tag = startBehaviorInPage(ListBehavior.description());

        assertThat(tag.getName(), is(equalTo("dl")));
    }

    @Test
    public void horizontalDescriptionList() {
        startBehaviorInPage(ListBehavior.description().horizontal());

        assertClassNamesPresent("row");
    }

    @Test
    public void unstyledUnorderedList() {
        startBehaviorInPage(ListBehavior.unordered().unstyled());

        assertClassNamesPresent("list-unstyled");
    }

    @Test
    public void inlineUnorderedList() {
        startBehaviorInPage(ListBehavior.unordered().inline());

        assertClassNamesPresent("list-inline");
    }
}
