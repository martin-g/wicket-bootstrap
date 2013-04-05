package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * Combine sets of <div class="btn-group"> into a <div class="btn-toolbar"> for more complex components.
 *
 * @author miha
 */
public final class ToolbarBehavior extends Behavior {

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "div");
        Attributes.addClass(tag, "btn-toolbar");
    }

    @Override
    public void unbind(Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
    }
}