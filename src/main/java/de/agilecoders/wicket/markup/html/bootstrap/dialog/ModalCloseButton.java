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
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ModalCloseButton extends Link<String> {

    private ButtonBehavior buttonBehavior;

    public ModalCloseButton() {
        this(Model.of("Close"));
    }

    public ModalCloseButton(IModel<String> label) {
        super("button", label);

        setBody(getDefaultModel());
        buttonBehavior = new ButtonBehavior(ButtonType.Default);

        add(new AttributeModifier("data-dismiss", "modal"));
        add(buttonBehavior);
    }

    public ModalCloseButton type(ButtonType buttonType) {
        buttonBehavior.withType(buttonType);
        return this;
    }

    public ModalCloseButton size(ButtonSize buttonSize) {
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
            Component parent;
            while ((parent = getParent()) != null) {
                if (parent instanceof Modal) {
                    setAnchor(parent);
                    break;
                }
            }
        }
    }
}
