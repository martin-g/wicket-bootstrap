package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
abstract public class InputBorder extends Border {

    public static enum Type {
        Addon("addon"), Button("btn");

        private final String type;

        private Type(String type) {
            this.type = type;
        }
    }

    private InputBorder(String id) {
        super(id);
    }

    public InputBorder(String id, IconType iconType) {
        this(id);

        addComponent(new Icon(id(), iconType));
    }

    public InputBorder(String id, IModel<String> label) {
        this(id);

        addComponent(new Label(id(), label));
    }

    public InputBorder(String id, Component component) {
        this(id);

        addComponent(component);
    }


    protected final void addComponent(Component component) {
        String inputGroupType = "input-group-" + getInputGroupType().type;
        component.add(new CssClassNameAppender(inputGroupType));
        addToBorder(component);
    }

    protected abstract String id();

    /**
     * @return The type of the input-group
     */
    protected Type getInputGroupType() {
        return Type.Addon;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, "input-group");
    }
}
