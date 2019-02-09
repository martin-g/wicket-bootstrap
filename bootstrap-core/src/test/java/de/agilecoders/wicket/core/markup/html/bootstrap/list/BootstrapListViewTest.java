package de.agilecoders.wicket.core.markup.html.bootstrap.list;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link BootstrapListView}
 *
 * @author miha
 */
public class BootstrapListViewTest extends WicketApplicationTest {
    private static final String MARKUP = "<ol wicket:id=\"id\"><li wicket:id=\"sub\"></li></ol>";

    @Test
    public void listViewIsRendered() {
        TagTester tag = startComponentInPage(new BootstrapListView<String>(id(), Lists.newArrayList("item1")) {
            @Override
            protected void populateItem(ListItem<String> components) {
                components.add(new Label("sub", components.getModelObject()));
            }
        }, MARKUP);

        assertThat(tag.getName(), is(equalTo("ol")));
        tester().assertContains("item1");
    }

    @Test
    public void listViewWithModelIsRendered() {
        TagTester tag = startComponentInPage(new BootstrapListView<String>(id(), Model.ofList(Lists.newArrayList("item1"))) {
            @Override
            protected void populateItem(ListItem<String> components) {
                components.add(new Label("sub", components.getModelObject()));
            }
        }, MARKUP);

        assertThat(tag.getName(), is(equalTo("ol")));
        tester().assertContains("item1");
    }

}
