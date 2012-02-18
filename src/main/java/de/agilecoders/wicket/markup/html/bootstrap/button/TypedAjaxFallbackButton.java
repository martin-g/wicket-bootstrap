package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.ComponentTag;
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

    private final IModel<ButtonType> buttonType;
    private IModel<ButtonSize> buttonSize;

    public TypedAjaxFallbackButton(String id, Form<?> form, IModel<ButtonType> buttonType) {
        this(id, Model.<String>of(), form, buttonType);
    }

    public TypedAjaxFallbackButton(String id, IModel<String> model, Form<?> form, IModel<ButtonType> buttonType) {
        super(id, model, form);

        this.buttonType = buttonType;
        this.buttonSize = Model.of(ButtonSize.Medium);
    }

    public TypedAjaxFallbackButton setSize(ButtonSize buttonSize) {
        this.buttonSize.setObject(buttonSize);

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new ButtonCssClassAppender(buttonType, buttonSize));
    }

    @Override
    protected final void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        if (!tag.getName().equalsIgnoreCase("a") && !tag.getName().equalsIgnoreCase("button")) {
            checkComponentTag(tag, "a or button");
        }
    }


}
