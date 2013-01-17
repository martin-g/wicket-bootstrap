package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default button behavior that controls the size and type
 * of a button.
 *
 * @author miha
 */
public class ButtonBehavior extends BootstrapBaseBehavior {

    private final IModel<ButtonType> buttonType;
    private final IModel<ButtonSize> buttonSize;
    private final IModel<Boolean> block;

    /**
     * Construct. Uses {@link ButtonSize#Medium} and {@link ButtonType#Default}.
     */
    public ButtonBehavior() {
        this(ButtonType.Default, ButtonSize.Medium);
    }

    /**
     * Construct. Uses {@link ButtonType#Default}.
     *
     * @param buttonSize Size of button
     */
    public ButtonBehavior(final ButtonSize buttonSize) {
        this(ButtonType.Default, buttonSize);
    }

    /**
     * Construct. Uses {@link ButtonSize#Medium}.
     *
     * @param buttonType Type of button
     */
    public ButtonBehavior(final ButtonType buttonType) {
        this(buttonType, ButtonSize.Medium);
    }

    /**
     * Construct.
     *
     * @param buttonType Type of button
     * @param buttonSize Size of button
     */
    public ButtonBehavior(final ButtonType buttonType, final ButtonSize buttonSize) {
        this(Model.of(buttonType), Model.of(buttonSize));
    }

    /**
     * Construct.
     *
     * @param buttonType Type of button
     * @param buttonSize Size of button
     */
    public ButtonBehavior(final IModel<ButtonType> buttonType, final IModel<ButtonSize> buttonSize) {
        this.buttonType = buttonType;
        this.buttonSize = buttonSize;
        this.block = Model.of(false);
    }

    /**
     * @return true, if button should be rendered as block element
     */
    public boolean isBlock() {
        return block.getObject();
    }

    /**
     * @return size of button
     */
    public ButtonSize size() {
        return buttonSize.getObject();
    }

    /**
     * @return type of button
     */
    public ButtonType type() {
        return buttonType.getObject();
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
     * @param buttonType type to use
     * @return this instance for chaining
     */
    public final ButtonBehavior withType(ButtonType buttonType) {
        this.buttonType.setObject(buttonType);
        return this;
    }

    /**
     * sets the size of button
     *
     * @param buttonSize size to use
     * @return this instance for chaining
     */
    public final ButtonBehavior withSize(ButtonSize buttonSize) {
        this.buttonSize.setObject(buttonSize);
        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        // a menu button has no css classes, inherits its styles from the menu
        if (!ButtonType.Menu.equals(type())) {
            component.add(new ButtonCssClassAppender(buttonType, buttonSize, block));
        }
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        // HACK issue #79: wicket changes tag name if component wasn't enabled
        if (!component.isEnabled()) {
            if (component instanceof AbstractLink) {
                tag.setName("a");
            } else if (component instanceof Button) {
                tag.setName("button");
            } else {
                if (tag.getAttribute("value") != null) {
                    tag.setName("input");
                } else {
                    tag.setName("button");
                }
            }

            tag.put("disabled", "disabled");
        }

        Components.assertTag(component, tag, "a", "button", "input");
    }
}
