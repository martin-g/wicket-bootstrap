package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default {@link AjaxButton} which is styled by bootstrap
 *
 * @author miha
 * @version 1.0
 */
public abstract class TypedAjaxButton extends AjaxButton implements BootstrapButton<TypedAjaxButton>, Invertible {

    private Icon icon;
    private ButtonBehavior buttonBehavior;

    public TypedAjaxButton(final String componentId, final ButtonType buttonType) {
        this(componentId, new Model<String>(), buttonType);
    }

    public TypedAjaxButton(final String componentId, final IModel<String> model, final ButtonType buttonType) {
        super(componentId, model);

        commonInit(buttonType, ButtonSize.Medium);
    }

    public TypedAjaxButton(String id, Form<?> form, ButtonType buttonType) {
        this(id, Model.<String>of(), form, buttonType);
    }

    public TypedAjaxButton(String id, IModel<String> model, Form<?> form, ButtonType buttonType) {
        super(id, model, form);

        commonInit(buttonType, ButtonSize.Medium);
    }

    private void commonInit(ButtonType buttonType, ButtonSize buttonSize) {
        buttonBehavior = new ButtonBehavior(buttonType, buttonSize);
        add(buttonBehavior);

        this.icon = new Icon("icon", IconType.NULL);
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param icon the new button icon
     * @return reference to the current instance
     */
    public TypedAjaxButton setIcon(Icon icon) {
        this.icon = icon.invert();

        return this;
    }

    public TypedAjaxButton setSize(ButtonSize buttonSize) {
        buttonBehavior.withSize(buttonSize);

        return this;
    }

    public void setInverted(final boolean inverted) {
        icon.setInverted(inverted);
    }
}
