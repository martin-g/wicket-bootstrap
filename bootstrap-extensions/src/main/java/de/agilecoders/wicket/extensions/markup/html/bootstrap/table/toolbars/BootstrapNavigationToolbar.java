package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator.Size;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.navigation.BootstrapNavigatorLabel;

/**
 * Bootstrap styled toolbar that displays links used to navigate the pages of the datatable as well as a message
 * about which rows are being displayed and their total number in the data table .
 *
 * @author Eric Hamel <eric.hamel@me.com>
 */
public class BootstrapNavigationToolbar extends AbstractToolbar {
	private static final long serialVersionUID = 1L;

	private static final String _PAGING_NAVIGATOR_ID = "navigator";
	private static final String _NAVIGATOR_LABEL_PANEL_ID = "navigatorLabelPanel";
	private final BootstrapPagingNavigator.Size size;

	/**
	 * Construct.
	 *
	 * Default Size will be used.
	 *
	 * @param table the pageable table
	 */
	public BootstrapNavigationToolbar(final DataTable<?, ?> table) {
		this(table, Size.Default);
	}

	/**
	 * Construct.
	 *
	 * @param table the pageable table
	 * @param size paging navigator size to use
	 */
	public BootstrapNavigationToolbar(final DataTable<?, ?> table, BootstrapPagingNavigator.Size size) {
		super(table);
		this.size = size;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		WebMarkupContainer span = new WebMarkupContainer("span");
		add(span);
		span.add(AttributeModifier.replace("colspan", () -> String.valueOf(getTable().getColumns().size())));
		span.add(newPagingNavigator(_PAGING_NAVIGATOR_ID, getTable(), size));
		span.add(newNavigatorLabel(_NAVIGATOR_LABEL_PANEL_ID, getTable(), size));
	}

	/**
	 * Factory method used to create the paging navigator that will be used by the datatable
	 *
	 * @param navigatorId component id the navigator should be created with
	 * @param table dataview used by datatable
	 * @param size paging navigator size to use
	 * @return paging navigator that will be used to navigate the data table
	 */
	protected BootstrapPagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table, final BootstrapPagingNavigator.Size size) {
		return new BootstrapPagingNavigator(navigatorId, table) {
			private static final long serialVersionUID = 1L;

			@Override
			public Size getSize() {
				return size;
			}
		};
	}

	/**
	 * Factory method used to create the navigator label that will be used by the datatable
	 *
	 * @param navigatorId component id navigator label should be created with
	 * @param table dataview used by datatable
	 * @param size paging navigator size to use
	 * @return navigator label that will be used to navigate the data table
	 *
	 */
	protected Panel newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table, final BootstrapPagingNavigator.Size size) {
		return new BootstrapNavigatorLabel(navigatorId, table, size);
	}

	/** {@inheritDoc} */
	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(getTable().getPageCount() > 1);
	}

}
