package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

/**
 * User: bcousin
 * add pull-right bootstrap css class on component
 */
public class PullRightBehavior extends BootstrapBaseBehavior {
    public static final String PULL_RIGHT = "pull-right";
    private IModel<Boolean> pullRight;

    public PullRightBehavior(final IModel<Boolean> pullRight) {
        this.pullRight = pullRight;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);
        if (pullRight.getObject()) {
            Attributes.addClass(tag, PULL_RIGHT);
        }
    }
}
