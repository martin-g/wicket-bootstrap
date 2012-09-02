package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 * @version 1.0
 */
public class SplitButton extends DropDownButton {

    private final AbstractLink baseButton;
    private final WebMarkupContainer caret;

    /**
     * Construct.
     *
     * @param markupId   The markup id
     * @param baseButton The main button
     */
    public SplitButton(final String markupId, final AbstractLink baseButton) {
        super(markupId, new Model<String>());

        add(this.baseButton = baseButton);
        add(this.caret = new WebMarkupContainer("caret"));
    }

    @Override
    protected void addBaseButton(String markupId) {
        // do nothing
    }

    @Override
    protected void updateButtonBehavior(ButtonType buttonType, ButtonSize buttonSize) {
        baseButton.add(new ButtonBehavior(buttonType, buttonSize));
        caret.add(new ButtonBehavior(buttonType, buttonSize));
    }
}
