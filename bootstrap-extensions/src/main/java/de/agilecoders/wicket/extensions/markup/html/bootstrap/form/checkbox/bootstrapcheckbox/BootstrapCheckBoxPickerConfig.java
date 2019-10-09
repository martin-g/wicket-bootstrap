package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Configuration object for the settings of
 * <a href="http://vsn4ik.github.io/bootstrap-checkbox/">Bootstrap-Checkbox</a>
 */
public class BootstrapCheckBoxPickerConfig extends AbstractConfig {

    private static final IKey<String> DisabledCursor = newKey("disabledCursor", "not-allowed");
    private static final IKey<String> DefaultClass = newKey("cls", null);
    private static final IKey<String> OffClass = newKey("offCls", "btn-danger ");
    private static final IKey<String> OnClass = newKey("onCls", "btn-success");
    private static final IKey<IconType> OffIconCLass = newKey("offIconCls", null);
    private static final IKey<IconType> OnIconCLass = newKey("onIconCls", null);
    private static final IKey<Boolean> Reverse = newKey("reverse", false);
    private static final IKey<String[]> ToggleKeyCodes = newKey("toggleKeyCodes", null);
    private static final IKey<String> GroupCls = newKey("groupCls", null);

    /**
     * Defines all possible button types.
     * @see <a href="http://getbootstrap.com/css/#buttons">Buttons</a>
     */
    public enum Size implements ICssClassNameProvider {
        Default(null),
        Small("btn-group-sm"),
        Large("btn-group-lg");

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of button type
         */
        Size(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        /**
         * @return css class name of button type
         */
        @Override
        public String cssClassName() {
            return cssClassName;
        }

    }
    
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

    public BootstrapCheckBoxPickerConfig withReverse(final boolean value) {
        put(Reverse, value);
        return this;
    }

    public BootstrapCheckBoxPickerConfig withToggleKeyCodes(final String[] value) {
        put(ToggleKeyCodes, value);
        return this;
    }
    
    public BootstrapCheckBoxPickerConfig withSize(final Size size) {
    	put(GroupCls, size.cssClassName());
    	return this;
    }
}
