package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * This class was added to make migration a bit easier.
 *
 * @author miha
 */
@Deprecated
public class TypedAjaxFallbackButton extends BootstrapAjaxFallbackButton {

    /**
     * {@inheritDoc}
     */
    public TypedAjaxFallbackButton(String id, Form<?> form, Buttons.Type type) {
        super(id, form, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedAjaxFallbackButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form, type);
    }
}
