package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ContainerBehavior extends BootstrapBaseBehavior {

    private Layout layout;

    public ContainerBehavior() {
        this.layout = Layout.Fixed;
    }

    public ContainerBehavior(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, layout.cssClassName());
    }

    public ContainerBehavior layout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public Layout layout() {
        return layout;
    }
}
