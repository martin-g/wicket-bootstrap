package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import org.apache.wicket.Application;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.markup.html.WebMarkupContainer;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.sort.BootstrapOrderByBorder;

/**
 * Toolbar that displays column headers. If the column is sortable a sortable header will be
 * displayed stylized with {@link IconType}
 *
 * By default:
 * ascending {@link FontAwesome6IconType#sort_up_s}
 * descending {@link FontAwesome6IconType#sort_down_s}
 * unsorted  {@link  FontAwesome6IconType#sort_s} -> unsorted
 *
 * @author Eric Hamel <eric.hamel@me.com>
 *
 * @param <S> the type of the sorting parameter
 */
public class BootstrapHeadersToolbar<S> extends HeadersToolbar<S>{
	private static final long serialVersionUID = 1L;

	public <T> BootstrapHeadersToolbar(DataTable<T, S> table, ISortStateLocator<S> stateLocator) {
		super(table, stateLocator);
	}

	@Override
	protected WebMarkupContainer newSortableHeader(String headerId, S property, ISortStateLocator<S> locator) {
		return new BootstrapOrderByBorder<>(headerId, property, locator) {
			private static final long serialVersionUID = 1L;

			@Override
			protected IconType ascendingIconType() {
				return FontAwesomeSettings.get(Application.get()).getIconType(FontAwesomeSettings.IconKey.SORT_UP);
			}

			@Override
			protected IconType descendingIconType() {
				return FontAwesomeSettings.get(Application.get()).getIconType(FontAwesomeSettings.IconKey.SORT_DOWN);
			}

			@Override
			protected IconType unsortedIconType() {
				return FontAwesomeSettings.get(Application.get()).getIconType(FontAwesomeSettings.IconKey.SORT);
			}
		};
	}
}
