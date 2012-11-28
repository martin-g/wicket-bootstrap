package de.agilecoders.wicket.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A simple close button for the {@link Modal} component.
 *
 * @author miha
 */
public class ModalCloseButton extends Link<String> {

    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     */
    public ModalCloseButton() {
        this(Model.of("Close"));
    }

    /**
     * Construct.
     *
     * @param label The button label
     */
    public ModalCloseButton(final IModel<String> label) {
        super("button", label);

        setBody(getDefaultModel());
        buttonBehavior = new ButtonBehavior(ButtonType.Default);

        add(new AttributeModifier("data-dismiss", "modal"));
        add(buttonBehavior);
    }

    /**
     * sets the button type
     *
     * @param buttonType The button type to use
     * @return this instance for chaining
     */
    public ModalCloseButton type(final ButtonType buttonType) {
        buttonBehavior.withType(buttonType);
        return this;
    }

    /**
     * sets the button size
     *
     * @param buttonSize The button size
     * @return this instance for chaining
     */
    public ModalCloseButton size(final ButtonSize buttonSize) {
        buttonBehavior.withSize(buttonSize);
        return this;
    }

    @Override
    public void onClick() {
        // nothing to do here;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (getAnchor() == null) {
            Component parent = getParent();
            while (parent != null) {
                if (parent instanceof Modal) {
                    setAnchor(parent);
                    break;
                } else {
                    parent = parent.getParent();
                }
            }
        }
    }
}
