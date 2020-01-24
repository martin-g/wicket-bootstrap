package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Combine sets of <div class="btn-group"> into a <div class="btn-toolbar"> for more complex components.
 *
 * @author miha
 */
public class Toolbar extends WebMarkupContainer {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param markupId the component' id
     */
    public Toolbar(final String markupId) {
        this(markupId, null);
    }

    /**
     * Construct.
     *
     * @param markupId the component' id
     * @param model    the data model
     */
    public Toolbar(final String markupId, final IModel<?> model) {
        super(markupId, model);

        add(new ToolbarBehavior());
    }
}
