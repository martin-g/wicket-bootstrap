package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * An Icon component displays a non localizable image (icon) resource.
 *
 * @author miha
 */
public class IconBehavior extends Behavior {
    private static final long serialVersionUID = 1L;
    private final IModel<IconType> type;
    private final IModel<String> value;

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     */
    public IconBehavior(final IconType type) {
        this(Model.of(type));
    }

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     */
    public IconBehavior(final IModel<IconType> type) {
        super();

        this.type = type;
        this.value = Model.of("");
    }


    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "i", "span");
        Attributes.addClass(tag, value.getObject());
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        if (hasIconType()) {
            value.setObject(type.getObject().cssClassName());
        } else {
            value.setObject("");
            component.setVisible(false);
        }
    }

    /**
     * @return true, if an {@link IconType} is set
     */
    public final boolean hasIconType() {
        return type != null && type.getObject() != null;
    }


    /**
     * sets a new icon type
     *
     * @return this instance for chaining
     */
    public final IconBehavior setType(final IconType iconType) {
        type.setObject(iconType);
        return this;
    }

    /**
     * @return the current icon type
     */
    public final IconType type() {
        return type.getObject();
    }


    @Override
    public void detach(Component component) {
        super.detach(component);

        type.detach();
        value.detach();
    }
}
