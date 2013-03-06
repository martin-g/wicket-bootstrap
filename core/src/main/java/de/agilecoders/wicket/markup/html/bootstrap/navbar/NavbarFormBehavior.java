package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * Adds all necessary css class names to a navbar form.
 *
 * @author miha
 */
public class NavbarFormBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        if (!"form".equals(tag.getName())) {
            tag.setName("form");
        }

        Attributes.addClass(tag, "navbar-form");

        super.onComponentTag(component, tag);
    }

}
