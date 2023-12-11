package de.agilecoders.wicket.samples.panels;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.panel.PanelType;

/**
 * @author Jan Ferko
 */
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
