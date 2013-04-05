package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

/**
 * A bootstrap styled {@link Button}
 *
 * @author miha
 */
public class MenuButton extends BootstrapButton {

    /**
     * Construct.
     */
    public MenuButton() {
        super(ButtonList.getButtonMarkupId(), Buttons.Type.Menu);
    }

    /**
     * Construct.
     *
     * @param model The label
     */
    public MenuButton(final IModel<String> model) {
        super(ButtonList.getButtonMarkupId(), model, Buttons.Type.Menu);
    }
}
