package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Bootstrap {@link ColorPickerTextField} CSS reference.
 *
 * @author ceefour
 */
public class ColorPickerTextFieldCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final ColorPickerTextFieldCssReference INSTANCE = new ColorPickerTextFieldCssReference();

    /**
     * @return the single instance of the resource reference
     */
    public static ColorPickerTextFieldCssReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private ColorPickerTextFieldCssReference() {
        super(ColorPickerTextFieldCssReference.class, "css/bootstrap-colorpicker.css");
    }
}
