package de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

/**
 * A specialization of {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink}
 * that disables itself during the Ajax call and shows a loading spinner
 */
public abstract class SpinnerAjaxLink<T> extends BootstrapAjaxLink<T> {
    private static final long serialVersionUID = 1L;
    /**
     * The behavior that sets the Ladda UI specific CSS classes and attributes
     */
    private final SpinnerBehavior laddaBehavior = new SpinnerBehavior();

    /**
     * Constructor.
     *
     * @param id The component id
     * @param type The Bootstrap type of the button
     */
    public SpinnerAjaxLink(String id, Buttons.Type type) {
        super(id, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to use for the label
     * @param type The Bootstrap type of the button
     */
    public SpinnerAjaxLink(String id, IModel<T> model, Buttons.Type type) {
        super(id, model, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to use for the label
     * @param type The Bootstrap type of the button
     * @param labelModel The model for the link's label
     */
    public <L extends Serializable> SpinnerAjaxLink(String id, IModel<T> model, Buttons.Type type, IModel<L> labelModel) {
        super(id, model, type, labelModel);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(laddaBehavior);
    }

    /**
     * Sets the effect to use
     *
     * @param effect The effect to use
     * @return {@code this}, for chaining
     */
    public SpinnerAjaxLink<T> setEffect(SpinnerBehavior.Effect effect) {
        this.laddaBehavior.withEffect(effect);
        return this;
    }

    /**
     * Sets the color for the spinner
     *
     * @param color The color for the spinner
     * @return {@code this}, for chaining
     */
    public SpinnerAjaxLink<T> setSpinnerColor(SpinnerBehavior.Color color) {
        this.laddaBehavior.withSpinnerColor(color);
        return this;
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new SpinnerAjaxCallListener());
    }

    @Override
    protected <L extends Serializable> Component newLabel(String markupId, IModel<L> model) {
        Component label = super.newLabel(markupId, model);
        label.setRenderBodyOnly(false);
        return label;
    }
    
    @Override
    protected void onConfigure() {
    	super.onConfigure();
    	Component label = get("label");
        if (Strings.isEmpty(label.getDefaultModelObjectAsString())) {
        	label.add(AttributeModifier.append("class", "sr-only"));
        }
    }
}
