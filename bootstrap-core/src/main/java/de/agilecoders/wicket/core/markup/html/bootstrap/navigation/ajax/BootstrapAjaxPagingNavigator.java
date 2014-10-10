package de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigation;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationBehavior;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationIncrementLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.AbstractRepeater;

/**
 * A paging navigator with Ajax capabilities and Bootstrap styles.
 */
public class BootstrapAjaxPagingNavigator extends BootstrapPagingNavigator {

    private final IPageable pageable;

    public BootstrapAjaxPagingNavigator(String id, IPageable pageable) {
        this(id, pageable, null);
    }

    public BootstrapAjaxPagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        super(id, pageable, labelProvider);

        this.pageable = pageable;
        setOutputMarkupId(true);
    }

    /**
     * Create a new increment link. May be subclassed to make use of specialized links, e.g. Ajaxian
     * links.
     *
     * @param id
     *            the link id
     * @param pageable
     *            the pageable to control
     * @param increment
     *            the increment
     * @return the increment link
     */
    @Override
    protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment)
    {
        return new AjaxPagingNavigationIncrementLink(id, pageable, increment) {
            @Override
            protected AjaxPagingNavigationBehavior newAjaxPagingNavigationBehavior(IPageable pageable, String event) {
                return new BootstrapAjaxPagingNavigationBehavior(this, pageable, event);
            }
        };
    }

    /**
     * Create a new pagenumber link. May be subclassed to make use of specialized links, e.g.
     * Ajaxian links.
     *
     * @param id
     *            the link id
     * @param pageable
     *            the pageable to control
     * @param pageNumber
     *            the page to jump to
     * @return the pagenumber link
     */
    @Override
    protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageNumber)
    {
        return new AjaxPagingNavigationLink(id, pageable, pageNumber) {
            @Override
            protected AjaxPagingNavigationBehavior newAjaxPagingNavigationBehavior(IPageable pageable, String event) {
                return new BootstrapAjaxPagingNavigationBehavior(this, pageable, event);
            }
        };
    }

    /**
     * @see org.apache.wicket.markup.html.navigation.paging.PagingNavigator#newNavigation(java.lang.String,
     *      org.apache.wicket.markup.html.navigation.paging.IPageable,
     *      org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider)
     */
    @Override
    protected PagingNavigation newNavigation(final String id, final IPageable pageable,
                                             final IPagingLabelProvider labelProvider)
    {
        return new AjaxPagingNavigation(id, pageable, labelProvider) {
            @Override
            protected Link<?> newPagingNavigationLink(String id, IPageable pageable, long pageIndex) {
                return new AjaxPagingNavigationLink(id, pageable, pageIndex) {
                    @Override
                    protected AjaxPagingNavigationBehavior newAjaxPagingNavigationBehavior(IPageable pageable, String event) {
                        return new BootstrapAjaxPagingNavigationBehavior(this, pageable, event);
                    }
                };
            }
            
            /** Attribute for active state */
            private final AttributeModifier activeAttribute = AttributeModifier.append("class", "active");
            
            @Override
            protected void populateItem(final LoopItem loopItem) {
                super.populateItem(loopItem);
                if ((getStartIndex() + loopItem.getIndex()) == pageable.getCurrentPage()) {
                    loopItem.add(activeAttribute);
                }
            }
        };
    }

    /**
     * Override this method to specify the markup container where your IPageable is part of. This
     * implementation is a default implementation that tries to find a parent markup container and
     * update that container. This is necessary as ListViews can't be updated themselves.
     *
     * @param target
     *            the request target to add the components that need to be updated in the ajax
     *            event.
     */
    protected void onAjaxEvent(AjaxRequestTarget target)
    {
        // update the container (parent) of the pageable, this assumes that
        // the pageable is a component, and that it is a child of a web
        // markup container.

        Component container = ((Component)pageable);
        // no need for a nullcheck as there is bound to be a non-repeater
        // somewhere higher in the hierarchy
        while (container instanceof AbstractRepeater)
        {
            container = container.getParent();
        }
        target.add(container);

        // in case the navigator is not contained by the container, we have
        // to add it to the response
        if (!((MarkupContainer) container).contains(this, true))
        {
            target.add(this);
        }
    }
}
