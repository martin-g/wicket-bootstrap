package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;


import de.agilecoders.wicket.jquery.AbstractConfig;

/**
 * Configuration holder for all {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.ColorPickerTextField} configurations.
 */
public class ColorPickerConfig extends AbstractConfig {

    public static enum Formats {
        hex {
            @Override
            public String to() {
                return "toHex()";
            }
        },
        rgb {
            @Override
            public String to() {
                return "toRgb()";
            }
        },
        rgba {
            @Override
            public String to() {
                return "toRgba()";
            }
        };

        /**
         * @return The JavaScript function to use to format the value of the selected color
         */
        public abstract String to();

        @Override
        public String toString() {
            return name();
        }
    }

    /**
     * The format of the color
     */
    private static final IKey<Integer> Format = newKey("format", Formats.hex.ordinal());

    /**
     * A flag indicating whether the color picker is used as a component
     * See <a href="http://www.eyecon.ro/bootstrap-colorpicker/">Bootstrap Color Picker</a> for details
     */
    private boolean isComponent = false;

    /**
     * A flag indicating whether the color picker should send the newly
     * selected value in an Ajax request.
     * @see ColorPickerTextField#onChange(org.apache.wicket.ajax.AjaxRequestTarget, String)
     */
    private boolean isAjaxUpdate = false;

    /**
     * @return {@code true} if Ajax request with the new value should be made
     */
    public boolean isAjaxUpdate() {
        return isAjaxUpdate;
    }

    /**
     * @param isAjaxUpdate A flag whether Ajax request should be made with the newly selected value
     * @return {@code this} instance, for chaining
     */
    public ColorPickerConfig setAjaxUpdate(boolean isAjaxUpdate) {
        this.isAjaxUpdate = isAjaxUpdate;
        return this;
    }

    /**
     * @return {@code true} if the input field is part of a component
     */
    public boolean isComponent() {
        return isComponent;
    }

    /**
     * @param isComponent A flag whether the input field is part of a "component"
     * @return {@code this} instance, for chaining
     */
    public ColorPickerConfig setComponent(boolean isComponent) {
        this.isComponent = isComponent;
        return this;
    }

    /**
     * @return the color format
     */
    public Formats getFormat() {
        final Integer ordinal = get(Format);
        final Formats result;
        if (Formats.rgb.ordinal() == ordinal) {
            result = Formats.rgb;
        } else if (Formats.rgba.ordinal() == ordinal) {
            result = Formats.rgba;
        } else {
            result = Formats.hex;
        }
        return result;
    }

    public ColorPickerConfig withFormat(final Formats format) {
        put(Format, format.ordinal());
        return this;
    }
}
