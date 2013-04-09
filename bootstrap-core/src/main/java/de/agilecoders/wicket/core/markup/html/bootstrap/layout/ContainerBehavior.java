package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;

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

    public ContainerBehavior layout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public Layout layout() {
        return layout;
    }
}
