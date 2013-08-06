package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * This behavior adds special layout css classes to the component tag.
 *
 * @author miha
 */
public class ContainerBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "container");
    }
}
