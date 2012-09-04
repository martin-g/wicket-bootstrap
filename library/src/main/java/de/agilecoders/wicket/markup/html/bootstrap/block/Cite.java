package de.agilecoders.wicket.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Cite extends WebMarkupContainer {

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Cite(final String componentId) {
        this(componentId, null);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Cite(final String componentId, final IModel<?> model) {
        super(componentId, model);

        add(new CiteBehavior());
    }
}
