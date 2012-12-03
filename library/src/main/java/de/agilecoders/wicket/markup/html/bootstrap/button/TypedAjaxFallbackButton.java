package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * a bootstrap styled {@link AjaxFallbackButton}
 *
 * @author miha
 */
public abstract class TypedAjaxFallbackButton extends AjaxFallbackButton implements BootstrapButton<TypedAjaxFallbackButton>, Invertible {

    private final Icon icon;
    private final Label label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     *
     * @param id         the components id
     * @param form       The assigned form
     * @param buttonType the button type
     */
    public TypedAjaxFallbackButton(String id, Form<?> form, ButtonType buttonType) {
        this(id, Model.<String>of(), form, buttonType);
    }

    /**
     * Construct.
     *
     * @param id         the components id
     * @param model      The label
     * @param form       The assigned form
     * @param buttonType the button type
     */
    public TypedAjaxFallbackButton(String id, IModel<String> model, Form<?> form, ButtonType buttonType) {
        super(id, model, form);

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
    @Override
    public TypedAjaxFallbackButton setLabel(IModel<String> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public TypedAjaxFallbackButton setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }

    /**
     * sets the size of the button
     *
     * @param buttonSize The button size
     * @return this instance for chaining
     */
    public TypedAjaxFallbackButton setSize(ButtonSize buttonSize) {
        buttonBehavior.withSize(buttonSize);

        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param buttonType The type of the button
     * @return this instance for chaining
     */
    public TypedAjaxFallbackButton setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }

    /**
     * inverts the icon color
     *
     * @param inverted true, if inverted version should be used
     */
    public void setInverted(final boolean inverted) {
        icon.setInverted(inverted);
    }
}
