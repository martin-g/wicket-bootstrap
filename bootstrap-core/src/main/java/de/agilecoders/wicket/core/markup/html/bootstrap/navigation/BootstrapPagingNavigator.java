package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * A Wicket panel component to draw and maintain a complete page navigator, meant to be easily added
 * to any PageableListView. A navigation which contains links to the first and last page, the
 * current page +- some increment and which supports paged navigation bars (@see
 * PageableListViewNavigationWithMargin).
 *
 * @author miha
 */
public class BootstrapPagingNavigator extends PagingNavigator {

    /**
     * position of pagination component
     */
    public enum Position implements ICssClassNameProvider {
        Left, Centered, Right;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }

    }

    private final Model<String> positionModel;

    /**
     * Construct.
     *
     * @param markupId The components markup id
     * @param pageable The pageable component the page links are referring to.
     */
    public BootstrapPagingNavigator(final String markupId, final IPageable pageable) {
        this(markupId, pageable, null);
    }

    /**
     * Construct.
     *
     * @param markupId      The components markup id
     * @param pageable      The pageable component the page links are referring to.
     * @param labelProvider The label provider for the link text.
     */
    public BootstrapPagingNavigator(final String markupId, final IPageable pageable, final IPagingLabelProvider labelProvider) {
        super(markupId, pageable, labelProvider);

        positionModel = Model.of(Position.Left.cssClassName());

        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "pagination", positionModel.getObject());
    }

    /**
     * Create a new PagingNavigation. May be subclassed to make us of specialized PagingNavigation.
     * 
     * @param id
     *            The id of the navigation component
     * @param pageable
     *            the pageable component
     * @param labelProvider
     *            The label provider for the link text.
     * @return the navigation object
     */
    
    @Override
    protected PagingNavigation newNavigation(final String id, final IPageable pageable, final IPagingLabelProvider labelProvider) {
	return new PagingNavigation(id, pageable, labelProvider) {
	    @Override
	    protected void populateItem(final LoopItem loopItem) {
		super.populateItem(loopItem);
		if ((getStartIndex() + loopItem.getIndex()) == pageable.getCurrentPage()) {
		    loopItem.add(AttributeModifier.append("class", "active"));
		}
	    };
	};
    }
    
    /**
     * sets the position of the pagination component
     *
     * @param position The position
     * @return this instance for chaining
     */
    public BootstrapPagingNavigator setPosition(final Position position) {
        positionModel.setObject(position.cssClassName());
        return this;
    }
}
