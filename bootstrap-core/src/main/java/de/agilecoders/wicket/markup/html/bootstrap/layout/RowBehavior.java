package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class RowBehavior extends BootstrapBaseBehavior {

    private Layout layout;

    public RowBehavior() {
        this.layout = Layout.Fixed;
    }

    public RowBehavior(Layout layout) {
        this.layout = layout;
    }

    public RowBehavior layout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public Layout layout() {
        return layout;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        if (Layout.Fixed.equals(layout())) {
            component.add(new CssClassNameAppender("row"));
        } else {
            component.add(new CssClassNameAppender("row-fluid"));
        }
    }
}
