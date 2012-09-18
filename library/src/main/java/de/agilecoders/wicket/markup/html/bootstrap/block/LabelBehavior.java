package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * A Label is a highlighted text with rounded corners.
 *
 * @author miha
 * @version 1.0
 */
public class LabelBehavior extends BootstrapBaseBehavior {
    private LabelType type = LabelType.Default;

    public LabelType type() {
        return type;
    }

    public void type(LabelType type) {
        this.type = type;
    }

    protected String className() {
        return "label";
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new CssClassNameAppender(className(), type().cssClassName(className())));
    }
}
