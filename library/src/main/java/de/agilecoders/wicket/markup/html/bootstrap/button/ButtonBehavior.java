package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default button behavior that controls the size and type
 * of a button.
 *
 * @author miha
 */
public class ButtonBehavior extends BootstrapBaseBehavior {

    private final IModel<Buttons.Type> buttonType;
    private final IModel<Buttons.Size> buttonSize;
    private final IModel<Boolean> block;

    /**
     * Construct. Uses {@link de.agilecoders.wicket.markup.html.bootstrap.button.Buttons.Size#Medium} and {@link de.agilecoders.wicket.markup.html.bootstrap.button.Buttons.Type#Default}.
     */
    public ButtonBehavior() {
        this(Buttons.Type.Default, Buttons.Size.Medium);
    }

    /**
     * Construct. Uses {@link de.agilecoders.wicket.markup.html.bootstrap.button.Buttons.Type#Default}.
     *
     * @param size Size of button
     */
    public ButtonBehavior(final Buttons.Size size) {
        this(Buttons.Type.Default, size);
    }

    /**
     * Construct. Uses {@link de.agilecoders.wicket.markup.html.bootstrap.button.Buttons.Size#Medium}.
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
        this.block = Model.of(false);
    }

    /**
     * @return size of button
     */
    public Buttons.Size size() {
        return buttonSize.getObject();
    }

    /**
     * @return type of button
     */
    public Buttons.Type type() {
        return buttonType.getObject();
    }

    /**
     * @return whether this button is a block level element
     */
    public boolean block() {
        return block.getObject();
    }

    /**
     * sets this button to be a block element or not
     *
     * @param block true, for block mode
     * @return this instance for chaining
     */
    public final ButtonBehavior block(Boolean block) {
        this.block.setObject(block);
        return this;
    }

    /**
     * sets the type of button
     *
     * @param type type to use
     * @return this instance for chaining
     */
    public final ButtonBehavior withType(Buttons.Type type) {
        this.buttonType.setObject(type);
        return this;
    }

    /**
     * sets the size of button
     *
     * @param size size to use
     * @return this instance for chaining
     */
    public final ButtonBehavior withSize(Buttons.Size size) {
        this.buttonSize.setObject(size);
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "a", "button", "input");

        // HACK issue #79: wicket changes tag name if component wasn't enabled
        Buttons.fixDisabledState(component, tag);

        // a menu button has no css classes, inherits its styles from the menu
        if (!Buttons.Type.Menu.equals(type())) {
            Buttons.onComponentTag(component, tag, buttonSize.getObject(), buttonType.getObject(), new ICssClassNameProvider() {
                @Override
                public String cssClassName() {
                    return block() ? "btn-block" : "";
                }
            });
        }
    }
}
