package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.Invertible;
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
public class IconBehavior extends Behavior implements Invertible<IconBehavior> {

    private final IModel<IconType> type;
    private final IModel<String> value;
    private final IModel<Boolean> invert;

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     * @param invert whether to invert the icon or not
     */
    public IconBehavior(final IconType type, final boolean invert) {
        this(Model.of(type), invert);
    }

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     * @param invert whether to invert the icon or not
     */
    public IconBehavior(final IModel<IconType> type, final boolean invert) {
        super();

        this.type = type;
        this.value = Model.of("");
        this.invert = Model.of(invert);
    }

    /**
     * Construct.
     *
     * @param type The type of the icon, e.g. Search, Home, User,...
     */
    public IconBehavior(final IconType type) {
        this(Model.of(type), false);
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
            final String invertPostfix = isInverted() ? " icon-white" : "";

            value.setObject(type.getObject().cssClassName() + invertPostfix);
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
     * @return true, if the icon color is inverted
     */
    public boolean isInverted() {
        return this.invert.getObject();
    }

    /**
     * marks the icon as inverted.
     *
     * @return the component's current instance
     */
    public final IconBehavior invert() {
        setInverted(true);

        return this;
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
    public IconBehavior setInverted(final boolean value) {
        this.invert.setObject(value);
        return this;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        invert.detach();
        type.detach();
        value.detach();
    }
}
