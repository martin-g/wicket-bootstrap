package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;

/**
 * Combine sets of <div class="btn-group"> into a <div class="btn-toolbar"> for more complex components.
 *
 * @author miha
 * @version 1.0
 */
public final class ToolbarBehavior extends BootstrapBaseBehavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("div"));
        component.add(new CssClassNameAppender("btn-toolbar"));
    }
}