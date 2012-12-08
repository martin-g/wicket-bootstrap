package de.agilecoders.wicket.markup.html;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.head.filter.AbstractHeaderResponseFilter;
import org.apache.wicket.markup.head.filter.FilteringHeaderResponse;
import org.apache.wicket.markup.head.filter.OppositeHeaderResponseFilter;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.util.lang.Args;

import java.util.List;

/**
 * Decorates an original {@link IHeaderResponse} and renders all javascript
 * items ({@link JavaScriptHeaderItem}, {@link OnDomReadyHeaderItem} and {@link OnLoadHeaderItem})
 * to a specific {@link org.apache.wicket.markup.head.filter.HeaderResponseContainer}. The filter
 * name has to be set before to {@link de.agilecoders.wicket.settings.IBootstrapSettings#setJsResourceFilterName(String)}.
 *
 * @author miha
 */
public class RenderJavaScriptToFooterHeaderResponseDecorator implements IHeaderResponseDecorator {

    final List<FilteringHeaderResponse.IHeaderResponseFilter> filters;

    /**
     * Construct. Uses {@link de.agilecoders.wicket.settings.IBootstrapSettings#getJsResourceFilterName()}
     * as filter name.
     */
    public RenderJavaScriptToFooterHeaderResponseDecorator() {
        this(Bootstrap.getSettings().getJsResourceFilterName());
    }

    /**
     * Construct.
     *
     * @param filterName The name of the footer container
     */
    public RenderJavaScriptToFooterHeaderResponseDecorator(final String filterName) {
        Args.notEmpty(filterName, "filterName");

        filters = Lists.newArrayList();

        final AbstractHeaderResponseFilter jsAcceptingFilter = new AbstractHeaderResponseFilter(filterName) {
            public boolean accepts(HeaderItem item) {
                return item instanceof JavaScriptHeaderItem ||
                       item instanceof OnDomReadyHeaderItem ||
                       item instanceof OnLoadHeaderItem;
            }
        };

        filters.add(jsAcceptingFilter);
        filters.add(new OppositeHeaderResponseFilter("headBucket", jsAcceptingFilter));
    }

    /**
     * decorates the original {@link IHeaderResponse}
     *
     * @param response original {@link IHeaderResponse}
     * @return decorated {@link IHeaderResponse}
     */
    public IHeaderResponse decorate(final IHeaderResponse response) {
        return new FilteringHeaderResponse(response, "headBucket", filters);
    }
}
