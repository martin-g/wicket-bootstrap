package de.agilecoders.wicket.samples.panels.pagination;

import de.agilecoders.wicket.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.markup.html.bootstrap.navigation.InfiniteScrollingBehavior;
import org.apache.wicket.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A demo panel for {@link de.agilecoders.wicket.markup.html.bootstrap.navigation.BootstrapPagingNavigator}
 */
public class InfinitePaginationPanel extends AbstractPaginationPanel {

    private final InfiniteScrollingBehavior scrollingBehavior;
    private BootstrapPagingNavigator pager;

    public InfinitePaginationPanel(String id) {
        super(id);

        setOutputMarkupId(true);
        add(scrollingBehavior = new InfiniteScrollingBehavior());

        scrollingBehavior.setNavSelector(pager);
        scrollingBehavior.setItemSelector(this, ".item");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        scrollingBehavior.setNextSelector(pager.get("next"));
    }

    @Override
    protected Component createPager(String id) {
        pager = new BootstrapPagingNavigator(id, pageable);
        return pager;
    }

    @Override
    protected List<String> createData() {
        List<String> elements = new ArrayList<String>(1000);
        for (int i = 0, l = 1000; i < l; ++i) {
            elements.add("element #" + i);
        }
        return elements;
    }

    @Override
    protected int pageSize() {
        return 100;
    }
}
