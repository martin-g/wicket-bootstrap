package de.agilecoders.wicket.markup.html.bootstrap.heading;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 * TODO: assert tag name
 *
 * @author miha
 * @version 1.0
 */
public class Heading extends Label {

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Heading(final String componentId) {
        super(componentId);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param label The label text
     */
    public Heading(final String componentId, final String label) {
        super(componentId, label);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model which holds the label text
     */
    public Heading(final String componentId, final IModel<String> model) {
        super(componentId, model);
    }
}
