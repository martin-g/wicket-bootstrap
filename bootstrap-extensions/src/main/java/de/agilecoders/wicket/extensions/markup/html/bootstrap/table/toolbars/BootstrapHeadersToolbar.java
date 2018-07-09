package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.markup.html.WebMarkupContainer;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.sort.BootstrapOrderByBorder;

/**
 * Toolbar that displays column headers. If the column is sortable a sortable header will be
 * displayed stylized with {@link IconType}
 *
 * By default:
 * ascending {@link FontAwesomeIconType#sort_asc}
 * descending {@link FontAwesomeIconType#sort_desc}
 * unsorted  {@link  FontAwesomeIconType#sort} -> unsorted
 *
 * @author Eric Hamel <eric.hamel@me.com>
 *
 * @param <S> the type of the sorting parameter
 */
public class BootstrapHeadersToolbar<S> extends HeadersToolbar<S>{

	public <T> BootstrapHeadersToolbar(DataTable<T, S> table, ISortStateLocator<S> stateLocator) {
		super(table, stateLocator);
	}

	@Override
	protected WebMarkupContainer newSortableHeader(String headerId, S property, ISortStateLocator<S> locator) {
		return new BootstrapOrderByBorder<S>(headerId, property, locator) {

			@Override
			protected IconType ascendingIconType() {
				return FontAwesomeIconType.sort_asc;
			}

			@Override
			protected IconType descendingIconType() {
				return FontAwesomeIconType.sort_desc;
			}

			@Override
			protected IconType unsortedIconType() {
				return FontAwesomeIconType.sort;
			}
		};
	}
}
