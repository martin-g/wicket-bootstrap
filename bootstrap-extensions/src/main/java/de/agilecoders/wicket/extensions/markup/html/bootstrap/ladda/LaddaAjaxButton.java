package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

/**
 * A specialization of {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton}
 * that disables itself during the Ajax call and shows a loading spinner
 */
public class LaddaAjaxButton extends BootstrapAjaxButton {

    /**
     * The behavior that sets the Ladda UI specific CSS classes and attributes
     */
    private final LaddaBehavior laddaBehavior = new LaddaBehavior();

    /**
     * Constructor.
     *
     * @param id The component id
     * @param type The Bootstrap type of the button
     */
    public LaddaAjaxButton(String id, Buttons.Type type) {
        super(id, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to use for the label
     * @param type The Bootstrap type of the button
     */
    public LaddaAjaxButton(String id, IModel<String> model, Buttons.Type type) {
        super(id, model, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param form The form that this button will submit
     * @param type The Bootstrap type of the button
     */
    public LaddaAjaxButton(String id, Form<?> form, Buttons.Type type) {
        super(id, form, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to use for the label
     * @param form The form that this button will submit
     * @param type The Bootstrap type of the button
     */
    public LaddaAjaxButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form, type);
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
    public LaddaAjaxButton setEffect(LaddaBehavior.Effect effect) {
        this.laddaBehavior.withEffect(effect);
        return this;
    }

    /**
     * Sets the color for the spinner
     *
     * @param color The color for the spinner
     * @return {@code this}, for chaining
     */
    public LaddaAjaxButton setSpinnerColor(String color) {
        this.laddaBehavior.withSpinnerColor(color);
        return this;
    }

    /**
     * Sets the size of the spinner in pixels
     *
     * @param size The size of the spinner in pixels
     * @return {@code this}, for chaining
     */
    public LaddaAjaxButton setSpinnerSize(int size) {
        this.laddaBehavior.withSpinnerSize(size);
        return this;
    }
    
    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new LaddaAjaxCallListener());
    }

    @Override
    protected Component newLabel(String markupId, IModel<String> model) {
        Component label = super.newLabel(markupId, model);
        label.add(AttributeModifier.append("class", "ladda-label"));
        return label;
    }
}
