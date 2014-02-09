package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A pure client side tab. It is completely stateless.
 * 
 * @author reiern70
 */
public class ClientSideBootstrapTabbedPanel<T extends ITab> extends Panel {

	/**
	 * Constructor.
	 * @param id
	 * @param tabs
	 */
	public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs) {
		this(id, tabs, null);
	}

	/**
	 * Constructor.
	 * @param id
	 * @param model
	 */
	public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs, IModel<Integer> activeTabIndexModel) {
		super(id, activeTabIndexModel);
			
		Args.notEmpty(tabs, "tabs");
		
		WebMarkupContainer panelsContainer = newPanelsContainer("panelsContainer");
		add(panelsContainer);
		
		RepeatingView panels = new RepeatingView("panels");		
		panelsContainer.add(panels);
		WebMarkupContainer tabsContainer = newTabsContainer("tabsContainer");
		add(tabsContainer);
		RepeatingView tabsView = new RepeatingView("tabs");		
		tabsContainer.add(tabsView);
		int tabIndex = 1;
		for(T tab: tabs) {
			if(tab.isVisible()) {			
				WebMarkupContainer panel = createContentPanel(panels.newChildId(), tab, tabIndex, activeTabIndexModel);
				panels.add(panel);
				WebMarkupContainer tabPanel = createTabPanel(panels.newChildId(), tab, tabIndex, activeTabIndexModel, panel.getMarkupId());
				tabsView.add(tabPanel);
				tabIndex++;
			}
		}	
	}
	
	private WebMarkupContainer createTabPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel, String tabPanelId) {
		WebMarkupContainer tabPanel = new WebMarkupContainer(id);
		tabPanel.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {

			@Override
			public String getObject() {
				int activeTab = activeTabIndexModel!=null? activeTabIndexModel.getObject():1;
				boolean isActive = (tabIndex == activeTab);
				return isActive?"active":"";
			}
		}));
		WebMarkupContainer link = newLink("link", tabPanelId);
		tabPanel.add(link);
		link.add(newTitleLabel("title", wrap(tab.getTitle())));
		return tabPanel;
	}
	
	private WebMarkupContainer createContentPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel) {
		WebMarkupContainer panel = tab.getPanel(id);
		panel.setRenderBodyOnly(false);
		panel.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {

			@Override
			public String getObject() {
				int activeTab = activeTabIndexModel!=null? activeTabIndexModel.getObject():1;
				boolean isActive = (tabIndex == activeTab);
				return isActive?"tab-pane fade in active":"tab-pane fade";
			}
		}));
		panel.setOutputMarkupId(true);
		return panel;
	}
	
	/**
	 * Override to create a new title label.
	 * 
	 * @param id
	 * @param title
	 * @return
	 */
	protected Component newTitleLabel(final String id, IModel<String> title) {
		return new Label(id, title);
	}
	
	/**
	 * Override to create a new link.
	 * 
	 * @param id
	 * @param href
	 * @return
	 */
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
	
	/**
	 * Override to return a different container's panel CSS class.
	 * @return
	 */
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

	/**
	 * Override to return a different tabs CSS class.
	 * @return
	 */
	protected CharSequence getTabContainerCssClass() {
		return "nav nav-tabs";
	}

}
