package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
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
        return "active";
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
}
