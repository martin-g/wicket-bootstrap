package de.agilecoders.wicket.samples.panels.pagination;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

import org.apache.wicket.Component;

/**
 * A demo panel for {@link BootstrapPagingNavigator}
 */
public class PaginationPanel extends AbstractPaginationPanel {

    public PaginationPanel(String id) {
        super(id);

        setOutputMarkupId(true);
    }

    @Override
    protected Component createPager(String id) {
        return new BootstrapPagingNavigator(id, pageable);
    }
}
