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

    /**
     * Construct.
     */
    public RowBehavior() {
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "row");
    }
}
