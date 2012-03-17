package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedAjaxButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author miha
 * @version 1.0
 */
public abstract class NavbarAjaxButton extends TypedAjaxButton {
    private static final String COMPONENT_ID = "button";

    public NavbarAjaxButton(final ButtonType buttonType) {
        this(new Model<String>(), buttonType);
    }

    public NavbarAjaxButton(final IModel<String> model, final ButtonType buttonType) {
        super(COMPONENT_ID, model, buttonType);
    }

}
