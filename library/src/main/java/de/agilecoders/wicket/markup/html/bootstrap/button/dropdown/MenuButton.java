package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

/**
 * A bootstrap styled {@link Button}
 *
 * @author miha
 */
public class MenuButton extends TypedButton {

    /**
     * Construct.
     */
    public MenuButton() {
        super(ButtonList.getButtonMarkupId(), ButtonType.Menu);
    }

    /**
     * Construct.
     *
     * @param model The label
     */
    public MenuButton(final IModel<String> model) {
        super(ButtonList.getButtonMarkupId(), model, ButtonType.Menu);
    }
}
