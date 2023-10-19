package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.samples.panels.pagination.InfinitePaginationPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 */
public class InfiniteScrollingPage extends WebPage {

    /**
     * Constructor.
     *
     * @param parameters The query parameters
     */
    public InfiniteScrollingPage(final PageParameters parameters) {
        super(parameters);

        add(new InfinitePaginationPanel("infinite"));
    }
}
