package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default {@link AjaxLink} which is styled by bootstrap
 *
 * @author miha
 */
public abstract class TypedAjaxLink<T> extends AjaxLink<T> implements BootstrapButton<TypedAjaxLink>, Invertible {

    private final Icon icon;
    private final ButtonBehavior buttonBehavior;
    private final IModel<IconType> iconTypeModel;

    /**
     * Construct.
     *
     * @param id the components id
     * @param buttonType  the type of the button
     */
    public TypedAjaxLink(final String id, final ButtonType buttonType) {
        this(id, null, buttonType);
    }

    /**
     * Construct.
     *
     * @param id         The component id
     * @param model      mandatory parameter
     * @param buttonType the type of the button
     */
    public TypedAjaxLink(String id, IModel<T> model, ButtonType buttonType) {
        super(id, model);

        buttonBehavior = new ButtonBehavior(buttonType);
        add(buttonBehavior);

        this.iconTypeModel = Model.of(IconType.NULL);
        this.icon = new Icon("icon", iconTypeModel);
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public TypedAjaxLink setIconType(IconType iconType) {
        iconTypeModel.setObject(iconType);

        return this;
    }

    public TypedAjaxLink setSize(ButtonSize buttonSize) {
        buttonBehavior.withSize(buttonSize);

        return this;
    }

    public TypedAjaxLink setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }

    public void setInverted(final boolean inverted) {
        icon.setInverted(inverted);
    }
}
