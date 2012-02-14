package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DefaultButton extends TypedButton {

    /**
     * Constructor.
     *
     * @param componentId the wicket component id
     */
    public DefaultButton(final String componentId) {
        super(componentId, ButtonType.Default);
    }

    /**
     * Constructor.
     *
     * @param componentId the wicket component id
     * @param model       the component's label as model
     */
    public DefaultButton(final String componentId, final IModel<String> model) {
        super(componentId, model, ButtonType.Default);
    }
}
