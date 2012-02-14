package de.agilecoders.wicket.markup.html.bootstrap.button;

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

    public TypedButton(final String componentId, final ButtonType buttonType) {
        this(componentId, new Model<String>(), buttonType);
    }

    public TypedButton(final String componentId, final IModel<String> model, final ButtonType buttonType) {
        super(componentId, model);

        this.buttonType = Model.of(buttonType);
        this.buttonSize = Model.of(ButtonSize.Medium);
    }

    public TypedButton setSize(ButtonSize buttonSize) {
        this.buttonSize.setObject(buttonSize);

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new ButtonCssClassAppender(buttonType, buttonSize));
    }

    @Override
    protected final void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "a");
    }

}
