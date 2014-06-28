package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * Bootstrap {@link ColorPickerTextField} JavaScript reference.
 *
 * @author ceefour
 */
public class ColorPickerTextFieldJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final ColorPickerTextFieldJavaScriptReference INSTANCE = new ColorPickerTextFieldJavaScriptReference();

    /**
     * @return the single instance of the resource reference
     */
    public static ColorPickerTextFieldJavaScriptReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private ColorPickerTextFieldJavaScriptReference() {
        super(ColorPickerTextFieldJavaScriptReference.class, "js/bootstrap-colorpicker.js");
    }
}
