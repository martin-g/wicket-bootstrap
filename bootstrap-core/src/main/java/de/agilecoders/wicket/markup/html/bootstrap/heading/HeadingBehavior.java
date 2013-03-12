package de.agilecoders.wicket.markup.html.bootstrap.heading;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * Simple heading behavior that only asserts correct tag name and
 * includes the correct css files
 *
 * @author miha
 */
public class HeadingBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "h1", "h2", "h3", "h4", "h5", "h6");
    }
}
