package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
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
    protected WebMarkupContainer createButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
        final WebMarkupContainer container = super.createButton(markupId, labelModel, iconTypeModel);
        container.setVisible(false);

        return container;
    }

    @Override
    protected void addButtonBehavior(final IModel<ButtonType> buttonType, final IModel<ButtonSize> buttonSize) {
        baseButton.add(new ButtonBehavior(buttonType, buttonSize));
        caret.add(new ButtonBehavior(buttonType, buttonSize));
    }
}
