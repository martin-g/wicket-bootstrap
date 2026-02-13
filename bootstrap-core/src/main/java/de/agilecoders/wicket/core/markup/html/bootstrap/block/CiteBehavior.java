package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * #### Description
 *
 * A CiteBehavior just asserts the correct tag name and appends the
 * {@link BootstrapBaseBehavior}.
 *
 * #### Usage
 *
 * ```java
 * Label label = new Label("id", Model.of("text"));
 * label.add(new CiteBehavior());
 * ```
 *
 * ```html
 * {@code <cite wicket:id="id"></cite>}
 * ```
 */
public class CiteBehavior extends Behavior {

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

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "cite");
    }
}
