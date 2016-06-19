package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.sort;


import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * A Bootstrap stylized component that wraps markup with an OrderByLink. This has the advantage of being able to add
 * the attribute modifier to the wrapping element as opposed to the link, so that it can be attached
 * to &lt;th&gt; or any other element.
 *
 * A set of {@link IconType} must be provided.
 *
 * For example:
 *
 * &lt;th wicket:id="order-by-border"&gt;Heading&lt;/th&gt;
 *
 * @param <S> the type of the sorting parameter
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public abstract class BootstrapOrderByBorder<S> extends Border {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 *
	 * @param id the component id
	 * @param property the property to be sorted on
	 * @param stateLocator the state locator
	 */
	public BootstrapOrderByBorder(final String id, final S property, final ISortStateLocator<S> stateLocator) {
		super(id);

		OrderByLink<S> link = newOrderByLink("orderByLink", property, stateLocator);
		link.add(CssClassNameModifier.append("class", "show"));

		addToBorder(link);

		link.add(getBodyContainer());

		WebMarkupContainer iconSort = new WebMarkupContainer("iconSort");
		iconSort.setVisible(false);
		link.add(iconSort);

		SortOrder sortOrder = stateLocator.getSortState().getPropertySortOrder(property);

		if(SortOrder.ASCENDING == sortOrder && ascendingIconType() != null){

			iconSort.setVisible(true);
			iconSort.add(new IconBehavior(ascendingIconType()));

		}else if(SortOrder.DESCENDING == sortOrder && descendingIconType() != null){

			iconSort.setVisible(true);
			iconSort.add(new IconBehavior(descendingIconType()));

		}else if(unsortedIconType() != null){

			iconSort.setVisible(true);
			iconSort.add(new IconBehavior(unsortedIconType()));
		}
	}

	/**
	 * create new sort order toggling link
	 *
	 * @param id component id
	 * @param property sort property
	 * @param stateLocator sort state locator
	 * @return link
	 */
	protected OrderByLink<S> newOrderByLink(final String id, final S property, final ISortStateLocator<S> stateLocator) {
		return new OrderByLink<S>(id, property, stateLocator)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortChanged()
			{
				BootstrapOrderByBorder.this.onSortChanged();
			}
		};
	}

	/**
	 * This method is a hook for subclasses to perform an action after sort has changed
	 */
	protected void onSortChanged() {
		// noop
	}

	protected abstract IconType ascendingIconType();
	protected abstract IconType descendingIconType();
	protected abstract IconType unsortedIconType();
}
