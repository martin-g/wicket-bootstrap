package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A pure client side tab. It is completely stateless.
 * 
 * @author reiern70
 */
public class ClientSideBootstrapTabbedPanel<T extends ITab> extends Panel {

	public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs) {
		this(id, tabs, null);
	}

	/**
	 * @param id
	 * @param model
	 */
	public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs, IModel<Integer> model) {
		super(id, model);
		
		int activeTab = model!=null? model.getObject():1;
		
		Args.notEmpty(tabs, "tabs");
		
		WebMarkupContainer panelsContainer = newPanelsContainer("panelsContainer");
		add(panelsContainer);
		
		List<String> contentsIds = new ArrayList<String>();
		RepeatingView panels = new RepeatingView("panels");		
		panelsContainer.add(panels);
		int tabIndex = 1;
		for(T tab: tabs) {
			if(tab.isVisible()) {
				WebMarkupContainer panel = tab.getPanel(panels.newChildId());
				if(tabIndex == activeTab) {
					panel.add(new AttributeAppender("class", "tab-pane fade in active"));
				} else {
					panel.add(new AttributeAppender("class", "tab-pane fade"));
				}
				panel.setOutputMarkupId(true);
				contentsIds.add(panel.getMarkupId());
				panels.add(panel);
				tabIndex++;
			}
		}	
		WebMarkupContainer tabsContainer = newTabsContainer("tabsContainer");
		add(tabsContainer);
		RepeatingView tabsView = new RepeatingView("tabs");		
		tabsContainer.add(tabsView);
		int listPosition = 0;
		tabIndex = 1;
		for(T tab: tabs) {
			if(tab.isVisible()) {
				WebMarkupContainer tabPanel = new WebMarkupContainer(panels.newChildId());
				tabsView.add(tabPanel);	
				if(tabIndex == activeTab) {
					tabPanel.add(new AttributeAppender("class", "active"));
				} 
				WebMarkupContainer link = newLink("link", contentsIds.get(listPosition));
				tabPanel.add(link);
				link.add(newTitleLabel("title", tab.getTitle()));
				listPosition++;
				tabIndex++;
			}
		}
	}
	
	protected Component newTitleLabel(final String id, IModel<String> title) {
		return new Label(id, title);
	}
	
	protected WebMarkupContainer newLink(final String id, final String href) {
		return new WebMarkupContainer(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("data-toggle", "tab");
				tag.put("href", "#" + href);
			}
		};
	}
	
	protected WebMarkupContainer newPanelsContainer(final String id)
	{
		return new WebMarkupContainer(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("class", getPanelsContainerCssClass());
			}
		};
	}
	
	protected CharSequence getPanelsContainerCssClass() {
		return "tab-content";
	}
	
	protected WebMarkupContainer newTabsContainer(final String id)
	{
		return new WebMarkupContainer(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("class", getTabContainerCssClass());
			}
		};
	}

	protected CharSequence getTabContainerCssClass() {
		return "nav nav-tabs";
	}

}
