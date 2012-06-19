package de.agilecoders.wicket.markup.html.bootstrap.heading;

import de.agilecoders.wicket.markup.html.bootstrap.common.AssertTagNameBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 * TODO: add level?
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
        this(componentId, Model.<String>of());
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param label The label text
     */
    public Heading(final String componentId, final String label) {
        this(componentId, Model.of(label));
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model which holds the label text
     */
    public Heading(final String componentId, final IModel<String> model) {
        super(componentId, model);

        add(new AssertTagNameBehavior("h1", "h2", "h3", "h4", "h5", "h6"));
    }
}
