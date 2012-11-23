package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class TypedAjaxFallbackButton extends AjaxFallbackButton implements BootstrapButton<TypedAjaxFallbackButton> {

    private final ButtonBehavior buttonBehavior;

    public TypedAjaxFallbackButton(String id, Form<?> form, IModel<ButtonType> buttonType) {
        this(id, Model.<String>of(), form, buttonType);
    }

    public TypedAjaxFallbackButton(String id, IModel<String> model, Form<?> form, IModel<ButtonType> buttonType) {
        super(id, model, form);

        // fix
        buttonBehavior = new ButtonBehavior(buttonType.getObject(), ButtonSize.Medium);
        add(buttonBehavior);
    }

    public TypedAjaxFallbackButton setSize(ButtonSize buttonSize) {
        this.buttonBehavior.withSize(buttonSize);

        return this;
    }

    public TypedAjaxFallbackButton setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }
}
