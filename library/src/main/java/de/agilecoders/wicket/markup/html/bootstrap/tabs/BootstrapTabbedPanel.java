package de.agilecoders.wicket.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
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

    /**
     * {@inheritDoc}
     */
    public BootstrapTabbedPanel(String id, List<T> tabs) {
        super(id, tabs);

        commonInit();
    }

    /**
     * {@inheritDoc}
     */
    public BootstrapTabbedPanel(String id, List<T> tabs, IModel<Integer> model) {
        super(id, tabs, model);

        commonInit();
    }

    /**
     * common initializer
     */
    private void commonInit() {
        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "tabbable");
    }

    @Override
    protected String getSelectedTabCssClass() {
        return "active";
    }

    @Override
    protected String getLastTabCssClass() {
        return "";
    }
}
