package de.agilecoders.wicket.samples.panels.validation;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * modal with typical form
 *
 * @author Alexey Volkov
 * @since 18.09.2014
 */
public class SimpleFormModal extends Modal<String> {

    private static final long serialVersionUID = 4591006733317646110L;

    /**
     * @param markupId markup id
     * @param panel panels
     */
    public SimpleFormModal(String markupId, SimpleFormPanel panel) {
        super(markupId);
        add(panel.withAjax());
    }
}
