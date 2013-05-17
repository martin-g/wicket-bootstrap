package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author miha
 * @version 1.0
 */
public abstract class NavbarAjaxButton extends BootstrapAjaxButton {

    /**
     * Construct.
     *
     * @param type {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type} of this button instance
     */
    public NavbarAjaxButton(final Buttons.Type type) {
        this(new Model<String>(), type);
    }

    /**
     * Construct.
     *
     * @param model      label of this button
     * @param type {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type} of this button instance
     */
    public NavbarAjaxButton(final IModel<String> model, final Buttons.Type type) {
        super(Navbar.componentId(), model, type);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        if (!"a".equalsIgnoreCase(tag.getName()) && !"button".equalsIgnoreCase(tag.getName())) {
            tag.setName("a");
        }

        super.onComponentTag(tag);
    }


}
