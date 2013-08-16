package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * A Badge is a unread counter with rounded corners.
 *
 * @author miha
 */
public class BadgeBehavior extends Behavior {

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
        Attributes.addClass(tag, className());
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }

    protected String className() {
        return "badge";
    }

}
