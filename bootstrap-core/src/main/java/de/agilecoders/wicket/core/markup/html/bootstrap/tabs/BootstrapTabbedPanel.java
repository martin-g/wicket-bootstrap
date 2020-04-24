package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * Styled version of {@link TabbedPanel}.
 *
 * @author miha
 */
public class BootstrapTabbedPanel<T extends ITab> extends TabbedPanel<T> {

    public BootstrapTabbedPanel(String id, List<T> tabs) {
        this(id, tabs, null);
    }

    public BootstrapTabbedPanel(String id, List<T> tabs, IModel<Integer> model) {
        super(id, tabs, model);

        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
    }

    @Override
    protected String getSelectedTabCssClass() {
        return "";
    }

    @Override
    protected String getLastTabCssClass() {
        return "";
    }

	@Override
	protected String getTabContainerCssClass()
	{
		return "nav nav-tabs";
	}

    @Override
    protected WebMarkupContainer newLink(final String linkId, final int index) {
        return new Link<Void>(linkId) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();

                final int currentlyActiveTab = BootstrapTabbedPanel.this.getSelectedTab();

                if (index == currentlyActiveTab) {
                    // Add the .active class to the <a>
                    add(new CssClassNameAppender("active"));
                }
            }

            @Override
            public void onClick() {
                setSelectedTab(index);
            }
        };
    }
}
