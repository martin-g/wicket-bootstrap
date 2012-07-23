package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class TypedButton extends Button implements BootstrapButton<TypedButton> {

    private Icon icon;
    private final ButtonBehavior buttonBehavior;

    public TypedButton(final String componentId, final ButtonType buttonType) {
        this(componentId, new Model<String>(), buttonType);
    }

    public TypedButton(final String componentId, final IModel<String> model, final ButtonType buttonType) {
        super(componentId, model);

        buttonBehavior = new ButtonBehavior(buttonType, ButtonSize.Medium);
        add(buttonBehavior);

        this.icon = new Icon("icon", IconType.NULL);
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param icon the new button icon
     * @return reference to the current instance
     */
    public TypedButton setIcon(Icon icon) {
        this.icon = icon.invert();

        return this;
    }

    public TypedButton setSize(ButtonSize buttonSize) {
        this.buttonBehavior.withSize(buttonSize);

        return this;
    }

}
