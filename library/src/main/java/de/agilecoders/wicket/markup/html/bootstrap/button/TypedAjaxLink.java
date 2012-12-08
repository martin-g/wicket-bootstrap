package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * Default {@link AjaxLink} which is styled by bootstrap
 *
 * @author miha
 */
public abstract class TypedAjaxLink<T> extends AjaxLink<T> implements BootstrapButton<TypedAjaxLink<T>> {

    private final Icon icon;
    private final Label label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

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

        add(buttonBehavior = new ButtonBehavior(buttonType, ButtonSize.Medium));
        add(icon = new Icon("icon", IconType.NULL).invert());
        add(splitter = new WebMarkupContainer("splitter"));

        this.label = new Label("label", model);
        this.label.setRenderBodyOnly(true);
        add(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType());
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public TypedAjaxLink<T> setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public TypedAjaxLink<T> setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }

    public TypedAjaxLink<T> setSize(ButtonSize buttonSize) {
        buttonBehavior.withSize(buttonSize);

        return this;
    }

    public TypedAjaxLink<T> setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }

    public TypedAjaxLink<T> setInverted(final boolean inverted) {
        icon.setInverted(inverted);
        return this;
    }
}
