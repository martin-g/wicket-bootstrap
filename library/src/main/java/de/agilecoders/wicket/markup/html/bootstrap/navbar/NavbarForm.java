package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.form.BootstrapForm;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * A form implementation for the {@link Navbar} component which
 * behaves like a {@link org.apache.wicket.markup.html.panel.Panel}.
 *
 * @author miha
 * @version 1.0
 */
public class NavbarForm<T> extends BootstrapForm<T> {

    public NavbarForm(String componentId) {
        super(componentId);
    }

    public NavbarForm(String componentId, IModel<T> model) {
        super(componentId, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new NavbarFormBehavior());
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
