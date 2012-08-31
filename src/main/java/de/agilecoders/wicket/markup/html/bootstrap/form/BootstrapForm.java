package de.agilecoders.wicket.markup.html.bootstrap.form;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapForm<T> extends org.apache.wicket.markup.html.form.Form<T> {

    private FormBehavior formBehavior;

    /**
     * Constructs a from with no validation
     *
     * @param componentId See Component
     */
    public BootstrapForm(String componentId) {
        super(componentId);

        commonInit();
    }

    /**
     * @see org.apache.wicket.Component#Component(String, IModel)
     * @param componentId See Component
     * @param model See Component
     */
    public BootstrapForm(String componentId, IModel<T> model) {
        super(componentId, model);

        commonInit();
    }

    /**
     * Common code executed by constructors.
     */
    private void commonInit() {
        add(formBehavior = new FormBehavior());
    }
    
    public BootstrapForm<T> type(FormType type) {
        formBehavior.type(type);
        return this;
    }
}
