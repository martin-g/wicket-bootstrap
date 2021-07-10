package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * Default button behavior that controls the size and type
 * of a button.
 *
 * @author miha
 */
public class ButtonBehavior extends BootstrapBaseBehavior {
    private static final long serialVersionUID = 1L;
    private final IModel<Buttons.Type> buttonType;
    private final IModel<Buttons.Size> buttonSize;

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size#Medium} and {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type#Secondary}.
     */
    public ButtonBehavior() {
        this(Buttons.Type.Secondary, Buttons.Size.Medium);
    }

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type#Secondary}.
     *
     * @param size Size of button
     */
    public ButtonBehavior(final Buttons.Size size) {
        this(Buttons.Type.Secondary, size);
    }

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size#Medium}.
     *
     * @param type Type of button
     */
    public ButtonBehavior(final Buttons.Type type) {
        this(type, Buttons.Size.Medium);
    }

    /**
     * Construct.
     *
     * @param type Type of button
     * @param size Size of button
     */
    public ButtonBehavior(final Buttons.Type type, final Buttons.Size size) {
        this(Model.of(type), Model.of(size));
    }

    /**
     * Construct.
     *
     * @param buttonType Type of button
     * @param buttonSize Size of button
     */
    public ButtonBehavior(final IModel<Buttons.Type> buttonType, final IModel<Buttons.Size> buttonSize) {
        this.buttonType = buttonType;
        this.buttonSize = buttonSize;
    }

    /**
     * @return size of button
     */
    public Buttons.Size getSize() {
        return buttonSize.getObject();
    }

    /**
     * @return type of button
     */
    public Buttons.Type getType() {
        return buttonType.getObject();
    }

    /**
     * sets the type of button
     *
     * @param type type to use
     * @return this instance for chaining
     */
    public final ButtonBehavior setType(final Buttons.Type type) {
        this.buttonType.setObject(type);
        return this;
    }

    /**
     * sets the size of button
     *
     * @param size size to use
     * @return this instance for chaining
     */
    public final ButtonBehavior setSize(final Buttons.Size size) {
        this.buttonSize.setObject(size);
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (!component.isEnabledInHierarchy()) {
            tag.put("disabled", "disabled");
        }

        Components.assertTag(component, tag, "a", "button", "input");

        // a menu button or nav link has no button-related CSS classes, inherits its styles from the menu
        if (!Buttons.Type.Menu.equals(getType()) && !Buttons.Type.NavLink.equals(getType())) {
            Buttons.onComponentTag(component, tag,
                                   buttonSize.getObject(),
                                   buttonType.getObject());
        } else {
            Attributes.addClass(tag, buttonType.getObject());
        }
    }

    @Override
    public void detach(Component component) {
        super.detach(component);
        buttonType.detach();
        buttonSize.detach();
    }
}
