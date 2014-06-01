package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * <p>
 * 	A "pure" client side stateless tabs component. You use it as you would use {@link TabbedPanel}, 
 * 	but instead of generating links that trigger server round trips id does generates just "client
 * 	side" links. 
 * </p>
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmailcom)
 */
public class ClientSideBootstrapTabbedPanel<T extends ITab> extends GenericPanel<Integer> {

    /**
     * Constructor.
     *
     * @param id The component id
     * @param tabs A list of all tabs
     */
    public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs) {
        this(id, tabs, null);
    }

    /**
     * Constructor.
     *
     * @param id  The component id
     * @param tabs A list of all tabs
     * @param activeTabIndexModel The model saying which tab is the current active one
     */
    public ClientSideBootstrapTabbedPanel(String id, final List<T> tabs, IModel<Integer> activeTabIndexModel) {
        super(id, activeTabIndexModel);

        Args.notEmpty(tabs, "tabs");

        WebMarkupContainer panelsContainer = newTabsContentsContainer("panelsContainer");
        add(panelsContainer);
        RepeatingView panels = new RepeatingView("panels");
        panelsContainer.add(panels);
        WebMarkupContainer tabsContainer = newTabsContainer("tabsContainer");
        add(tabsContainer);
        RepeatingView tabsView = new RepeatingView("tabs");
        tabsContainer.add(tabsView);
        int tabIndex = 0;
        for (T tab: tabs) {
            if (tab.isVisible()) {
                WebMarkupContainer panel = createContentPanel(panels.newChildId(), tab, tabIndex, activeTabIndexModel);
                panels.add(panel);
                WebMarkupContainer tabPanel = createTabPanel(panels.newChildId(), tab, tabIndex, activeTabIndexModel, panel.getMarkupId());
                tabsView.add(tabPanel);
                tabIndex++;
            }
        }
    }

    // creates tabs panel.
    private WebMarkupContainer createTabPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel, String tabPanelId) {
        WebMarkupContainer tabPanel = new WebMarkupContainer(id);
        tabPanel.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                int activeTab = activeTabIndexModel!=null? activeTabIndexModel.getObject():0;
                boolean isActive = (tabIndex == activeTab);
                return "tab" + tabIndex + (isActive?" active":"");
            }
        }));
        WebMarkupContainer link = newTabLink("link", tabPanelId, tabIndex);
        tabPanel.add(link);
        link.add(newTabTitleLabel("title", wrap(tab.getTitle()), tabIndex));
        return tabPanel;
    }

    // creates tabs contents panel.
    private WebMarkupContainer createContentPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel) {
        WebMarkupContainer panel = tab.getPanel(id);
        panel.setRenderBodyOnly(false);
        panel.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                int activeTab = activeTabIndexModel!=null? activeTabIndexModel.getObject():0;
                boolean isActive = (tabIndex == activeTab);
                return "tab" + tabIndex + (isActive?" tab-pane fade in active":" tab-pane fade");
            }
        }));
        panel.setOutputMarkupId(true);
        return panel;
    }

    /**
     * Override to create a different title label.
     *
     * @param id
     * @param title The label title
     * @param tabIndex The index of the tab
     * @return
     */
    protected Component newTabTitleLabel(final String id, IModel<String> title, final int tabIndex) {
        return new Label(id, title);
    }

    /**
     * Override to create a different tab's link.
     *
     * @param id
     * @param href The href (id) of the corresponding tab's content.
     * @param tabIndex The index of the tab
     * @return
     */
    protected WebMarkupContainer newTabLink(final String id, final String href, final int tabIndex) {
        return new WebMarkupContainer(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("data-toggle", "tab");
                tag.put("href", "#" + href);
            }
        };
    }

    /**
     * Override to create a different tabs content's container.
     * @param id The component id fo the content's container
     * @return The content's container
     */
    protected WebMarkupContainer newTabsContentsContainer(final String id) {
        return new WebMarkupContainer(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("class", getPanelsContainerCssClass());
            }
        };
    }

    /**
     * Override to return a different CSS class for tabs contents panel container.
     * @return The CSS class for tabs contents panel container.
     */
    protected CharSequence getPanelsContainerCssClass() {
        return "tab-content";
    }

    /**
     * Override to returns a different tabs container.
     *
     * @param id The component id of the tabs container
     * @return The tabs container
     */
    protected WebMarkupContainer newTabsContainer(final String id) {
        return new WebMarkupContainer(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("class", getTabContainerCssClass());
            }
        };
    }

    /**
     * Override to return a different CSS class for tabs container.
     * @return The CSS class for tabs container
     */
    protected CharSequence getTabContainerCssClass() {
        return "nav nav-tabs";
    }

}
