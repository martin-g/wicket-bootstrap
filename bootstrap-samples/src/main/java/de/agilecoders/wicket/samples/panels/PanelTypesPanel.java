package de.agilecoders.wicket.samples.panels;

import java.util.Arrays;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import de.agilecoders.wicket.core.markup.html.bootstrap.panel.PanelType;

public class PanelTypesPanel extends Panel {

	public PanelTypesPanel(String id) {
		super(id);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new PropertyListView<PanelType>("panelTypes", Arrays.asList(PanelType.values())) {
			@Override
			protected void populateItem(ListItem<PanelType> item) {
				item.add(new Label("panelType", item.getModelObject().name()));
				item.add(new SimpleCard("panel").withPanelType(item.getModelObject()));
			}
		});
	}
}
