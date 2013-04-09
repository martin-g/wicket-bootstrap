package de.agilecoders.wicket.core.markup.html.bootstrap.heading;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Simple replacement of {@link Label} which can be used for all
 * h1 to h6-tags.
 *
 * @author miha
 */
public class Heading extends Label {

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Heading(final String componentId) {
        super(componentId);

        commonInit();
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param label       The label text
     */
    public Heading(final String componentId, final String label) {
        super(componentId, label);

        commonInit();
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       which holds the label text
     */
    public Heading(final String componentId, final IModel<String> model) {
        super(componentId, model);

        commonInit();
    }

    /**
     * adds the {@link HeadingBehavior} to this component
     */
    private void commonInit() {
        add(new HeadingBehavior());
    }
}
