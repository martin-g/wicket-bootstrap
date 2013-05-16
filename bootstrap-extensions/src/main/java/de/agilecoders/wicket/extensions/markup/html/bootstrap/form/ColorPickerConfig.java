package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.AbstractConfig;

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
     */
    private boolean isComponent = false;

    private boolean isAjaxUpdate = false;

    public boolean isAjaxUpdate() {
        return isAjaxUpdate;
    }

    public ColorPickerConfig setAjaxUpdate(boolean isAjaxUpdate) {
        this.isAjaxUpdate = isAjaxUpdate;
        return this;
    }

    public boolean isComponent() {
        return isComponent;
    }

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
