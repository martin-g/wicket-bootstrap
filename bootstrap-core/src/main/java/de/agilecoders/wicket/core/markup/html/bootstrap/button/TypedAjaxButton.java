package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * This class was added to make migration a bit easier.
 *
 * @author miha
 */
@Deprecated
public abstract class TypedAjaxButton extends BootstrapAjaxButton {

    /**
     * {@inheritDoc}
     */
    public TypedAjaxButton(String componentId, Buttons.Type type) {
        super(componentId, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedAjaxButton(String componentId, IModel<String> model, Buttons.Type type) {
        super(componentId, model, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedAjaxButton(String id, Form<?> form, Buttons.Type type) {
        super(id, form, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedAjaxButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form, type);
    }
}
