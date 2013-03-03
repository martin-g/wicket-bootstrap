package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.model.IModel;

/**
 * This class was added to make migration a bit easier.
 *
 * @author miha
 */
@Deprecated
public class TypedButton extends BootstrapButton {

    /**
     * {@inheritDoc}
     */
    public TypedButton(String componentId, Buttons.Type type) {
        super(componentId, type);
    }

    /**
     * {@inheritDoc}
     */
    public TypedButton(String componentId, IModel<String> model, Buttons.Type type) {
        super(componentId, model, type);
    }
}
