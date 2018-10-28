package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
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
 */
public class Collapsible extends Panel {

    /**
     * whether a tab is active or not
     */
    public enum State {
        Active, Inactive
    }

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
     * @param markupId  mandatory parameter
     * @param tabs      mandatory parameter
     * @param activeTab mandatory parameter
     */
    public Collapsible(final String markupId, final List<ITab> tabs, final IModel<Integer> activeTab) {
        super(markupId, activeTab);

        this.tabs = tabs;
        this.activeTab = activeTab;

        setOutputMarkupId(true);

        add(newTabList("tabs", tabs));

        BootstrapResourcesBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");

        Attributes.addClass(tag, "accordion");
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
                final CharSequence parentMarkupId = Strings2.getMarkupId(Collapsible.this);
                final ITab tab = Collapsible.this.tabs.get(loopItem.getIndex());
                final State state = activeTab.getObject().equals(loopItem.getIndex()) ? State.Active : State.Inactive;

                final Component container = newContainer("body", tab, state);
                final String containerMarkupId = container.getMarkupId(true);
                final Component title = newTitle("title", tab, state);

                title.add(new AttributeModifier("data-parent", "#" + parentMarkupId));
                title.add(new AttributeModifier("data-target", "#" + containerMarkupId));
                title.add(new AttributeModifier("aria-controls", containerMarkupId));

                container.add(new AttributeModifier("data-parent", "#" + parentMarkupId));
                container.add(new AttributeModifier("aria-labelledby", "#" + title.getMarkupId(true)));
                
                loopItem.add(title);
                loopItem.add(container);
            }
        };
    }

    /**
     * @return the active state css class name as {@link CssClassNameAppender}.
     */
    protected CssClassNameAppender getActiveCssClassNameAppender() {
        return new CssClassNameAppender("show");
    }

    /**
     * creates a new content container.
     *
     * @param markupId The markup id of the content container
     * @param tab      the current {@link ITab} implementation to render
     * @param state    the current tab state
     * @return new container.
     */
    protected Component newContainer(final String markupId, final ITab tab, final State state) {
        final WebMarkupContainer container = new WebMarkupContainer(markupId);
        container.setOutputMarkupId(true);
        container.add(tab.getPanel("content"));

        if (State.Active.equals(state)) {
            container.add(new AttributeModifier("aria-expand", "true"));
            container.add(getActiveCssClassNameAppender());
        }
        else {
        	container.add(new AttributeModifier("aria-expand", "false"));
        }

        return container;
    }

    /**
     * creates a new title component.
     *
     * @param markupId The markup id of the content container
     * @param tab      the current {@link ITab} implementation to render
     * @param state    the current tab state
     * @return new title label
     */
    protected Component newTitle(final String markupId, final ITab tab, final State state) {
        return new Label(markupId, tab.getTitle());
    }
}
