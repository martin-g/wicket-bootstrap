package de.agilecoders.wicket.samples.panels.pagination;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.PopoverBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import java.util.Arrays;
import java.util.List;

/**
 * A base panel for demos of paging navigators
 */
public abstract class AbstractPaginationPanel extends Panel {

    protected final PageableListView<String> pageable;

    public AbstractPaginationPanel(String id) {
        super(id);

        List<String> data = createData();

        pageable = new PageableListView<String>("pageable", data, pageSize()) {
            @Override
            protected void populateItem(ListItem<String> item) {
                onPopulateItem(item);
            }
        };
        add(pageable);

        add(createPager("pager"));
    }

    protected List<String> createData() {
        return Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");
    }

    protected void onPopulateItem(ListItem<String> item) {
        item.add(new Label("item", item.getModelObject())
                     .add(new PopoverBehavior(Model.of("popover" + item.getModelObject()), Model.of("body"))));
    }

    protected int pageSize() {
        return 2;
    }

    protected abstract Component createPager(String id);
}
