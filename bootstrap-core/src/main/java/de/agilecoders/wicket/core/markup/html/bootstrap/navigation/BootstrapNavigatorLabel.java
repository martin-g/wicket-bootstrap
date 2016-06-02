package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator.Size;
import de.agilecoders.wicket.core.util.Attributes;

public class BootstrapNavigatorLabel extends Panel{

	private static final String _NAVIGATOR_LABEL_ID = "navigatorLabel";
	
	/**
	 * Construct.
	 * 
	 * @param id the component id
	 * @param table the pageable table
	 * @param size the size of the label
	 */
	public BootstrapNavigatorLabel(String id, DataTable<?, ?> table, final BootstrapPagingNavigator.Size size) {
		super(id);

		WebMarkupContainer paginationStyleMarkupContainer = new WebMarkupContainer("paginationStyleMarkupContainer") {
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
		        checkComponentTag(tag, "ul");

		        Attributes.addClass(tag, "pagination");

		        if (size != null && !size.equals(Size.Default)) {
		            Attributes.addClass(tag, size.cssClass());
		        }
			}
		};
		add(paginationStyleMarkupContainer);
		
		paginationStyleMarkupContainer.add(newNavigatorLabel(_NAVIGATOR_LABEL_ID, table));
		
		

		
	}

	/**
	 * Override this method to provide your own implementation of NavigatorLabel
	 * @param navigatorLabelId the navigator label id
	 * @param table the pageable table
	 * @return the web component to display
	 */
	protected WebComponent newNavigatorLabel(String navigatorLabelId, DataTable<?, ?> table) {

		return new NavigatorLabel(_NAVIGATOR_LABEL_ID, table);
	}

}
