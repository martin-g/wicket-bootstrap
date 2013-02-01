package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * Combine sets of <div class="btn-group"> into a <div class="btn-toolbar"> for more complex components.
 *
 * @author miha
 */
public final class ToolbarBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "div");
        Attributes.addClass(tag, "btn-toolbar");
    }
}