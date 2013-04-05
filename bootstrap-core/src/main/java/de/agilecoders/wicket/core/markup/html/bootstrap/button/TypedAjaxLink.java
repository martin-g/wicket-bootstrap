package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.model.IModel;

/**
 * This class was added to make migration a bit easier.
 *
 * @author miha
 */
@Deprecated
public abstract class TypedAjaxLink<T> extends BootstrapAjaxLink<T> {

    /**
     * {@inheritDoc}
     */
    public TypedAjaxLink(String id, Buttons.Type type) {
        super(id, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedAjaxLink(String id, IModel<T> model, Buttons.Type type) {
        super(id, model, type);
    }
}
