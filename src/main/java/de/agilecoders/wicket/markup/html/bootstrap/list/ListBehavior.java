package de.agilecoders.wicket.markup.html.bootstrap.list;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ListBehavior extends BootstrapBaseBehavior {

    private boolean unstyled = false;

    public ListBehavior unstyled() {
        unstyled = true;

        return this;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        if (unstyled) {
            component.add(new CssClassNameAppender("unstyled"));
        }
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "ul", "ol");
    }

}
