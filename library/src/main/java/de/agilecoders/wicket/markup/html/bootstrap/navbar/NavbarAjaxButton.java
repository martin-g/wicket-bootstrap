package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedAjaxButton;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author miha
 * @version 1.0
 */
public abstract class NavbarAjaxButton extends TypedAjaxButton  {

    /**
     * Construct.
     *
     * @param buttonType {@link ButtonType} of this button instance
     */
    public NavbarAjaxButton(final ButtonType buttonType) {
        this(new Model<String>(), buttonType);
    }

    /**
     * Construct.
     *
     * @param model      label of this button
     * @param buttonType {@link ButtonType} of this button instance
     */
    public NavbarAjaxButton(final IModel<String> model, final ButtonType buttonType) {
        super(Navbar.COMPONENT_ID, model, buttonType);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        if (!"a".equalsIgnoreCase(tag.getName()) && !"button".equalsIgnoreCase(tag.getName())) {
            tag.setName("a");
        }

        super.onComponentTag(tag);
    }


}
