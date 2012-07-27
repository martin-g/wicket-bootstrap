package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BadgeBehavior extends BootstrapBaseBehavior {

    public enum Type implements CssClassNameProvider {
        Default, Success, Warning, Important, Info, Inverse;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "badge-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(cssClassName());
        }

    }
    private Type type = Type.Default;

    public Type type() {
        return type;
    }

    public void type(Type type) {
        this.type = type;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new CssClassNameAppender("badge", type().cssClassName()));
    }
}
