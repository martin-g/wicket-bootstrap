package de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.util.CssClassNames.Helper;

/**
 * A specialization of {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton}
 * that disables itself during the Ajax call and shows a loading spinner
 */
public class SpinnerAjaxButton extends BootstrapAjaxButton {
    private static final long serialVersionUID = 1L;
    /**
     * The behavior that sets the Ladda UI specific CSS classes and attributes
     */
    private final SpinnerBehavior spinnerBehavior = new SpinnerBehavior();

    /**
     * Constructor.
     *
     * @param id The component id
     * @param type The Bootstrap type of the button
     */
    public SpinnerAjaxButton(String id, Buttons.Type type) {
        super(id, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to use for the label
     * @param type The Bootstrap type of the button
     */
    public SpinnerAjaxButton(String id, IModel<String> model, Buttons.Type type) {
        super(id, model, type);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param form The form that this button will submit
     * @param type The Bootstrap type of the button
     */
    public SpinnerAjaxButton(String id, Form<?> form, Buttons.Type type) {
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
    public SpinnerAjaxButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form, type);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(spinnerBehavior);
    }

    /**
     * Sets the effect to use
     *
     * @param effect The effect to use
     * @return {@code this}, for chaining
     */
    public SpinnerAjaxButton setEffect(SpinnerBehavior.Effect effect) {
        this.spinnerBehavior.withEffect(effect);
        return this;
    }

    /**
     * Sets the color for the spinner
     *
     * @param color The color for the spinner
     * @return {@code this}, for chaining
     */
    public SpinnerAjaxButton setSpinnerColor(SpinnerBehavior.Color color) {
        this.spinnerBehavior.withSpinnerColor(color);
        return this;
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new SpinnerAjaxCallListener());
    }

    @Override
    protected void onConfigure() {
    	super.onConfigure();
    	Component label = get("label");
        if (Strings.isEmpty(label.getDefaultModelObjectAsString())) {
        	label.add(AttributeModifier.append("class", Helper.visuallyHidden));
        }
    }
}
