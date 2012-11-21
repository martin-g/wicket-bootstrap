package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * Adds all necessary css class names to a navbar form.
 *
 * @author miha
 * @version 1.0
 */
public class NavbarFormBehavior extends BootstrapBaseBehavior {

    private final CssClassNameAppender classNameAppender;

    /**
     * Construct.
     */
    public NavbarFormBehavior() {
        classNameAppender = new CssClassNameAppender("navbar-form");
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        if (!"form".equals(tag.getName())) {
            tag.setName("form");
        }

        super.onComponentTag(component, tag);
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(classNameAppender);
    }

    @Override
    public void unbind(Component component) {
        super.unbind(component);

        component.remove(classNameAppender);
    }
}
