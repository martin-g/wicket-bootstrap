package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * A behavior that changes the look of a span tag to look like a disabled input tag by adding some css styles.
 *
 * TODO: sync this with enabled flag on component
 *
 * @author miha
 */
public class UneditableInputBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
        Attributes.addClass(tag, "uneditable-input");
    }
}
