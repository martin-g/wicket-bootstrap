package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.test.Attributes;
import de.agilecoders.wicket.test.IntegrationTest;
import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Tests the {@link BootstrapPagingNavigator} component
 *
 * @author miha
 */
@Category(IntegrationTest.class)
public class BootstrapPagingNavigatorTest extends WicketApplicationTest {

    @Test
    public void correctClassNameIsSet() {
        BootstrapPagingNavigator navigator = createDefault();
        tester().startComponentInPage(navigator);

        Attributes.assertClassNamesPresent(tester().getTagByWicketId("pagination"), "pagination");
    }

    @Test
    public void correctClassNameIsSetForPositionLeft() {
        BootstrapPagingNavigator navigator = createDefault();
        navigator.setPosition(BootstrapPagingNavigator.Position.Left);
        tester().startComponentInPage(navigator);

        Attributes.assertSingleClassNamePresent(tester().getTagByWicketId("pagination"), "pagination");
    }

    @Test
    public void correctClassNameIsSetForPositionRight() {
        BootstrapPagingNavigator navigator = createDefault();
        navigator.setPosition(BootstrapPagingNavigator.Position.Right);
        tester().startComponentInPage(navigator);

        Attributes.assertClassNamesPresent(tester().getTagByWicketId("pagination"), "pagination", "pagination-right");
    }

    @Test
    public void correctClassNameIsSetForPositionCenter() {
        BootstrapPagingNavigator navigator = createDefault();
        navigator.setPosition(BootstrapPagingNavigator.Position.Centered);
        tester().startComponentInPage(navigator);

        Attributes.assertClassNamesPresent(tester().getTagByWicketId("pagination"), "pagination", "pagination-centered");
    }

    @Test(expected = MarkupException.class)
    public void correctTagNameIsAsserted() {
        BootstrapPagingNavigator navigator = createWithTagName("a");

        tester().startComponentInPage(navigator);
    }

    /**
     * @return a new {@link BootstrapPagingNavigator} with given tag
     */
    private BootstrapPagingNavigator createWithTagName(final String tagName) {
        return new BootstrapPagingNavigator("pagination", createPageable()) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                tag.setName(tagName);
                super.onComponentTag(tag);
            }
        };
    }

    /**
     * @return a new default {@link BootstrapPagingNavigator} with a "div" tag
     */
    private BootstrapPagingNavigator createDefault() {
        return createWithTagName("div");
    }

    /**
     * creates a pageable list view
     *
     * @return new {@link IPageable} instance
     */
    private IPageable createPageable() {
        return new PageableListView<String>("pageable", Generics2.newArrayList("item1", "item2", "item3", "item2"), 1) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("item", item.getModelObject()));
            }
        };
    }

}
