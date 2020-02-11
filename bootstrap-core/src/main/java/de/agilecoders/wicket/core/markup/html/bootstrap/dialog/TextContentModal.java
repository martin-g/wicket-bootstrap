package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * A simple modal dialog that shows a given text as body.
 *
 * @author miha
 * @version 1.0
 */
public class TextContentModal extends Modal<String> {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param markupId The markup id
     * @param model    The body content as text model
     */
    public TextContentModal(String markupId, IModel<String> model) {
        super(markupId, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("content", getModel()));
    }
}
