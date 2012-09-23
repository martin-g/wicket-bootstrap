package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ButtonBehavior extends BootstrapBaseBehavior {

    private final IModel<ButtonType> buttonType;
    private final IModel<ButtonSize> buttonSize;
    private final IModel<Boolean> block;

    /**
     * Construct.
     */
    public ButtonBehavior() {
        this(ButtonType.Default, ButtonSize.Medium);
    }

    public ButtonBehavior(final ButtonSize buttonSize) {
        this(ButtonType.Default, buttonSize);
    }

    public ButtonBehavior(final ButtonType buttonType) {
        this(buttonType, ButtonSize.Medium);
    }

    public ButtonBehavior(final ButtonType buttonType, final ButtonSize buttonSize) {
        this(Model.of(buttonType), Model.of(buttonSize));
    }

    public ButtonBehavior(final IModel<ButtonType> buttonType, final IModel<ButtonSize> buttonSize) {
        this.buttonType = buttonType;
        this.buttonSize = buttonSize;
        this.block = Model.of(false);
    }

    public boolean isBlock() {
        return block.getObject();
    }

    public ButtonSize size() {
        return buttonSize.getObject();
    }

    public ButtonType type() {
        return buttonType.getObject();
    }

    public final ButtonBehavior block(Boolean block) {
        this.block.setObject(block);
        return this;
    }

    public final ButtonBehavior withType(ButtonType buttonType) {
        this.buttonType.setObject(buttonType);
        return this;
    }

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

        Components.assertTag(component, tag, "a", "button", "input");
    }
}
