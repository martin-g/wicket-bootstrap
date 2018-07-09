package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Configuration object for the settings of
 * <a href="http://vsn4ik.github.io/bootstrap-checkbox/">Bootstrap-Checkbox</a>
 */
public class BootstrapCheckBoxPickerConfig extends AbstractConfig {

    private static final IKey<String> Style = newKey("style", null);
    private static final IKey<String> DefaultClass = newKey("defaultClass", "btn-secondary");
    private static final IKey<String> DisabledCursor = newKey("disabledCursor", "not-allowed");
    private static final IKey<String> OffClass = newKey("offClass", "btn-danger ");
    private static final IKey<String> OnClass = newKey("onClass", "btn-success");
    private static final IKey<IconType> OffIconCLass = newKey("offIconClass", null);
    private static final IKey<IconType> OnIconCLass = newKey("onIconClass", null);
    private static final IKey<Boolean> Reverse = newKey("reverse", false);
    private static final IKey<String[]> ToggleKeyCodes = newKey("toggleKeyCodes", null);

    /**
     * Default constructor
     */
    public BootstrapCheckBoxPickerConfig() {
    }

    public BootstrapCheckBoxPickerConfig withDefaultClass(final String value) {
        put(DefaultClass, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withDisabledCursor(final String value) {
        put(DisabledCursor, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withOffClass(final String value) {
        put(OffClass, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withOnClass(final String value) {
        put(OnClass, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withOffIcon(final IconType value) {
        put(OffIconCLass, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withOnIcon(final IconType value) {
        put(OnIconCLass, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withStyle(final ButtonGroup.Size value) {
        put(Style, value.cssClassName());
        return this;
    }

    public BootstrapCheckBoxPickerConfig withReverse(final boolean value) {
        put(Reverse, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withToggleKeyCodes(final String[] value) {
        put(ToggleKeyCodes, value);
        return this;
    }
}
