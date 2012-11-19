package de.agilecoders.wicket.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * A {@link Collapsible} panel contains a list of {@link ITab} implementations that
 * are collapsed and can be opened by click on title.
 * <p/>
 * Example:
 * <p/>
 * <pre>
 * List tabs=new ArrayList();
 * tabs.add(new AbstractTab(new Model&lt;String&gt;(&quot;first tab&quot;)) {
 *   public Panel getPanel(String panelId)
 *   {
 *     return new TabPanel1(panelId);
 *   }
 * });
 *
 * tabs.add(new AbstractTab(new Model&lt;String&gt;(&quot;second tab&quot;)) {
 *   public Panel getPanel(String panelId)
 *   {
 *     return new TabPanel2(panelId);
 *   }
 * });
 *
 * add(new Collapsible(&quot;tabs&quot;, tabs));
 *
 * &lt;div wicket:id=&quot;collapsible&quot;&gt;&lt;/div&gt;
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class Collapsible extends Panel {

    private final List<ITab> tabs;
    private final IModel<Integer> activeTab;

    /**
     * Construct. Marks the first tab active.
     *
     * @param markupId mandatory parameter
     * @param tabs     mandatory parameter
     */
    public Collapsible(final String markupId, final List<ITab> tabs) {
        this(markupId, tabs, Model.of(0));
    }

    /**
     * Construct.
     *
     * @param markupId        mandatory parameter
     * @param tabs      mandatory parameter
     * @param activeTab mandatory parameter
     */
    public Collapsible(final String markupId, final List<ITab> tabs, final IModel<Integer> activeTab) {
        super(markupId);

        this.tabs = tabs;
        this.activeTab = activeTab;

        setOutputMarkupId(true);

        add(newTabList("tabs", tabs));
        add(new BootstrapResourcesBehavior(),
            new CssClassNameAppender("accordion"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript(createInitializerScript(getMarkupId(true))));
    }

    /**
     * creates an initializer script for this {@link Collapsible} instance.
     *
     * @param markupId The markup id of this {@link Collapsible} instance
     * @return initializer script
     */
    protected CharSequence createInitializerScript(final String markupId) {
        return "$('#+" + markupId + "').collapse();";
    }

    /**
     * creates a new tab list.
     *
     * @param markupId The components markup id
     * @return the list view component (default: {@link Loop})
     */
    protected Component newTabList(String markupId, List<ITab> tabs) {
        return new Loop(markupId, tabs.size()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final LoopItem loopItem) {
                final String parentMarkupId = Collapsible.this.getMarkupId(true);
                final ITab tab = Collapsible.this.tabs.get(loopItem.getIndex());

                final Component container = newContainer("body", tab, activeTab.getObject().equals(loopItem.getIndex()));
                final Component title = newTitle("title", tab);

                title.add(new AttributeModifier("data-parent", "#" + parentMarkupId));
                title.add(new AttributeModifier("href", "#" + container.getMarkupId(true)));

                loopItem.add(title);
                loopItem.add(container);
            }
        };
    }

    /**
     * @return the active state css class name as {@link CssClassNameAppender}.
     */
    protected CssClassNameAppender getActiveCssClassNameAppender() {
        return new CssClassNameAppender("in");
    }

    /**
     * creates a new content container.
     *
     * @param markupId The markup id of the content container
     * @param tab      the current {@link ITab} implementation to render
     * @param active   whether this tab is active or not
     * @return new container.
     */
    protected Component newContainer(final String markupId, final ITab tab, final boolean active) {
        final WebMarkupContainer container = new WebMarkupContainer(markupId);
        container.setOutputMarkupId(true);
        container.add(tab.getPanel("content"));

        if (active) {
            container.add(getActiveCssClassNameAppender());
        }

        return container;
    }

    /**
     * creates a new title component.
     *
     * @param markupId The markup id of the content container
     * @param tab      the current {@link ITab} implementation to render
     * @return new title label
     */
    protected Component newTitle(final String markupId, final ITab tab) {
        return new Label(markupId, tab.getTitle());
    }
}
