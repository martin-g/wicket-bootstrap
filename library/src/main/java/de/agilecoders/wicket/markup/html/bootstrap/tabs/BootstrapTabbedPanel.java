package de.agilecoders.wicket.markup.html.bootstrap.tabs;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapTabbedPanel<T extends ITab> extends TabbedPanel<T> {
    public BootstrapTabbedPanel(String id, List<T> tabs) {
        super(id, tabs);

        commonInit();
    }

    public BootstrapTabbedPanel(String id, List<T> tabs, IModel<Integer> model) {
        super(id, tabs, model);

        commonInit();
    }

    private void commonInit() {
        add(new BootstrapBaseBehavior(),
            new CssClassNameAppender("tabbable"),
            new AssertTagNameBehavior("div"));
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
