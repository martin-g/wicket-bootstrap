package de.agilecoders.wicket.examples.ui.demo.buttons;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.heading.Heading;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ButtonsSpan extends Span {
    public ButtonsSpan(IModel<ButtonType> model) {
        super(model);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        ButtonType type = (ButtonType) getDefaultModelObject();
        add(new Heading("heading", type.name()));

        add(new TypedButton("small", type).setSize(ButtonSize.Small));
        add(new TypedButton("medium", type));
        add(new TypedButton("mediumDisabled", type).setEnabled(false));
        add(new TypedButton("large", type).setSize(ButtonSize.Large));
    }
}
