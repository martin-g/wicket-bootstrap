package de.agilecoders.wicket.core.markup.html;

import de.agilecoders.wicket.core.Bootstrap;

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

import java.util.ArrayList;
import java.util.List;

/**
 * #### Description
 *
 * Decorates an original {@link IHeaderResponse} and renders all javascript
 * items ({@link JavaScriptHeaderItem}, {@link OnDomReadyHeaderItem} and {@link OnLoadHeaderItem})
 * to a specific {@link org.apache.wicket.markup.head.filter.HeaderResponseContainer}. The filter
 * name has to be set before to {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#setJsResourceFilterName(String)}.
 *
 * #### Usage
 *
 * Add response decorator in your application init method. The default constructor uses the resource
 * filter name from bootstrap settings and the second one allows you to set the filter name manually.
 *
 * ```java
 * app.setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
 * // or (please use settings.setJsResourceFilterName("my-filter-name") instead)
 * app.setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator("my-filter-name"));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class RenderJavaScriptToFooterHeaderResponseDecorator implements IHeaderResponseDecorator {

    final List<FilteringHeaderResponse.IHeaderResponseFilter> filters;

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getJsResourceFilterName()}
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

        filters = new ArrayList<>();

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
