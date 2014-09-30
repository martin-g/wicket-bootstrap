package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.model.IModel;

/**
 * A default {@link BootstrapButton} which uses all default styles
 * of bootstrap (mostly grey)
 *
 * @author miha
 */
public class DefaultBootstrapButton extends BootstrapButton {

    /**
     * Constructor.
     *
     * @param componentId the wicket component id
     */
    public DefaultBootstrapButton(final String componentId) {
        super(componentId, Buttons.Type.Default);
    }

    /**
     * Constructor.
     *
     * @param componentId the wicket component id
     * @param model       the component's label as model
     */
    public DefaultBootstrapButton(final String componentId, final IModel<String> model) {
        super(componentId, model, Buttons.Type.Default);
    }
}
