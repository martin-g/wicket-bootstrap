package de.agilecoders.wicket.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * A Cite just asserts the correct tag name and appends the
 * {@link de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior}.
 *
 * @author miha
 * @version 1.0
 */
public class Cite extends WebMarkupContainer {

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     */
    public Cite(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     * @param model       the component's model
     */
    public Cite(final String markupId, final IModel<?> model) {
        super(markupId, model);

        add(new CiteBehavior());
    }
}
