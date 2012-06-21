package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.ComponentTag;
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

    private final IModel<ButtonType> buttonType;
    private IModel<ButtonSize> buttonSize;
    private Icon icon;

    public TypedButton(final String componentId, final ButtonType buttonType) {
        this(componentId, new Model<String>(), buttonType);
    }

    public TypedButton(final String componentId, final IModel<String> model, final ButtonType buttonType) {
        super(componentId, model);

        this.buttonType = Model.of(buttonType);
        this.buttonSize = Model.of(ButtonSize.Medium);

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
        this.buttonSize.setObject(buttonSize);

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new ButtonCssClassAppender(buttonType, buttonSize));
        //add(icon);
    }

    @Override
    protected final void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        if (!tag.getName().equalsIgnoreCase("a") && !tag.getName().equalsIgnoreCase("button")) {
            checkComponentTag(tag, "a or button");
        }
    }


}
