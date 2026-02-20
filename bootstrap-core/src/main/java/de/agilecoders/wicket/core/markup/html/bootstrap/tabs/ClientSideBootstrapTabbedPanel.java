package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * <p>
 * 	A "pure" client side stateless tabs component. You use it as you would use {@link TabbedPanel},
 * 	but instead of generating links that trigger server round trips id does generate just "client
 * 	side" links.
 *
 * 	To keep the current selected tab active after a page refresh one could set the "shouldNotifyTabChange" variable to
 * 	true. In that case the server will be notified via AJAX and the "activeTabIndexModel" is updated. The
 * 	"shouldNotifyTabChange" variable only has an effect if the "activeTabIndexModel" is not null.
 * </p>
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmailcom)
 */
public class ClientSideBootstrapTabbedPanel<T extends ITab> extends GenericPanel<Integer> {
    private static final long serialVersionUID = 1L;
    private final List<T> tabs;

    private boolean shouldNotifyTabChange;

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
        this.tabs = tabs;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
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
                WebMarkupContainer panel = createContentPanel(panels.newChildId(), tab, tabIndex, getModel());
                panels.add(panel);
                WebMarkupContainer tabPanel = createTabPanel(panels.newChildId(), tab, tabIndex, getModel(), panel.getMarkupId());
                tabsView.add(tabPanel);
                tabIndex++;
            }
        }
    }

    public ClientSideBootstrapTabbedPanel<T> withShouldNotifyTabChange(final boolean shouldNotifyTabChange) {
        this.shouldNotifyTabChange = shouldNotifyTabChange;
        return this;
    }

    // creates tabs panel.
    private WebMarkupContainer createTabPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel, String tabPanelId) {
        WebMarkupContainer tabPanel = new WebMarkupContainer(id);
        tabPanel.add(new CssClassNameAppender("tab" + tabIndex));

        WebMarkupContainer link = newTabLink("link", tabPanelId, tabIndex);
        link.add(new AttributeModifier("class", () -> {
            final int activeTab = activeTabIndexModel != null ? activeTabIndexModel.getObject() : 0;
            final boolean isActive = (tabIndex == activeTab);
            return isActive ? "active" : "";
        }) {
            @Override
            protected Serializable newValue(final String currentValue, final String replacementValue) {
                return currentValue + " " + replacementValue;
            }
        });
        link.add(new Behavior() {
            @Override
            public void onConfigure(Component component) {
                super.onConfigure(component);
                final List<ActiveTabClickAjaxEventBehavior> activeTabClickAjaxEventBehaviors = link.getBehaviors(ActiveTabClickAjaxEventBehavior.class);
                if (shouldNotifyTabChange && Objects.nonNull(activeTabIndexModel)) {
                    // avoid duplicated behaviors on component
                    if (activeTabClickAjaxEventBehaviors.isEmpty()) {
                        link.add(new ActiveTabClickAjaxEventBehavior(tabIndex, activeTabIndexModel));
                    }
                } else {
                    activeTabClickAjaxEventBehaviors.forEach(link::remove);
                }
            }
        });
        tabPanel.add(link);
        link.add(newTabTitleLabel("title", wrap(tab.getTitle()), tabIndex));
        return tabPanel;
    }

    // creates tabs contents panel.
    private WebMarkupContainer createContentPanel(String id, T tab, final int tabIndex, final IModel<Integer> activeTabIndexModel) {
        WebMarkupContainer panel = tab.getPanel(id);
        panel.setRenderBodyOnly(false);
        panel.add(new AttributeModifier("class", () -> {
            int activeTab = activeTabIndexModel != null ? activeTabIndexModel.getObject() : 0;
            boolean isActive = (tabIndex == activeTab);
            return "tab" + tabIndex + (isActive ? " tab-pane fade show active" : " tab-pane fade");
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
                tag.put("data-bs-toggle", "tab");
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

    private static class ActiveTabClickAjaxEventBehavior extends AjaxEventBehavior {

        private final int tabIndex;
        private final IModel<Integer> activeTabIndexModel;

        /**
         * Constructor
         *
         * @param tabIndex the Index of the Tab the Behavior belongs to
         * @param activeTabIndexModel the Model that hold the index value of the current active tab
         */
        public ActiveTabClickAjaxEventBehavior(final int tabIndex, final IModel<Integer> activeTabIndexModel) {
            super("click");
            this.tabIndex = tabIndex;
            this.activeTabIndexModel = activeTabIndexModel;
        }

        @Override
        protected void onEvent(final AjaxRequestTarget target) {
            if (Objects.nonNull(activeTabIndexModel)) {
                activeTabIndexModel.setObject(tabIndex);
            }
        }
    }
}
