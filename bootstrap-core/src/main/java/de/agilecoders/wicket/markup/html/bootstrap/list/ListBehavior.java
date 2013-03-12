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

    private enum Type {
        DYNAMIC, OL, UL, DL
    }

    private boolean unstyled = false;
    private boolean horizontal;
    private Type type = Type.DYNAMIC;

    public ListBehavior description() {
        type = Type.DL;

        return this;
    }

    public ListBehavior unordered() {
        type = Type.UL;

        return this;
    }

    public ListBehavior ordered() {
        type = Type.OL;

        return this;
    }

    public ListBehavior horizontal() {
        horizontal = true;

        return this;
    }

    public ListBehavior unstyled() {
        unstyled = true;

        return this;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        if (unstyled && Type.UL.equals(type)) {
            component.add(new CssClassNameAppender("unstyled"));
        }

        if (horizontal && Type.DL.equals(type)) {
            component.add(new CssClassNameAppender("dl-horizontal"));
        }
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (!Type.DYNAMIC.equals(type)) {
            tag.setName(type.name().toLowerCase());
        }

        Components.assertTag(component, tag, "ul", "ol", "dl");
    }

}
