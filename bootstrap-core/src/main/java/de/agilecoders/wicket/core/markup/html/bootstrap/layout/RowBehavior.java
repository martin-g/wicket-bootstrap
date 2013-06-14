package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * The {@link RowBehavior} adds the "row" css class to the
 * component tag according to given layout.
 *
 * @author miha
 */
public class RowBehavior extends BootstrapBaseBehavior {

    private final Layout layout;

    /**
     * Construct using {@link Layout#Fixed} as default layout
     */
    public RowBehavior() {
        this.layout = Layout.Fixed;
    }

    /**
     * Construct using given layout
     */
    public RowBehavior(Layout layout) {
        this.layout = layout;
    }

    /**
     * @return the row layout that is used.
     */
    public Layout layout() {
        return layout;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, Layout.Fixed.equals(layout()) ? "row" : "row-fluid");
    }
}
