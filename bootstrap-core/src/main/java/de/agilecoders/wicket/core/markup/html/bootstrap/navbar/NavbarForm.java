package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * A form implementation for the {@link Navbar} component which
 * behaves like a {@link org.apache.wicket.markup.html.panel.Panel}.
 *
 * @author miha
 */
public class NavbarForm<T> extends BootstrapForm<T> {

    /**
     * Construct.
     *
     * @param componentId the component id
     */
    public NavbarForm(String componentId) {
        super(componentId);
    }

    /**
     * Construct.
     *
     * @param componentId the component id
     * @param model the form model
     */
    public NavbarForm(String componentId, IModel<T> model) {
        super(componentId, model);
    }

    @Override
    public void onComponentTag(ComponentTag tag) {
        if (!"form".equals(tag.getName())) {
            tag.setName("form");
        }

        super.onComponentTag(tag);
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }
}
