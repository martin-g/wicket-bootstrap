package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Orientation;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Configuration object for the settings of
 * <a href="http://vsn4ik.github.io/bootstrap-checkbox/">Bootstrap-Checkbox</a>
 */
public class BootstrapCheckBoxPickerConfig extends AbstractConfig {

    private static final IKey<String> 	BaseGroupCls = 		newKey("baseGroupCls", "btn-group");
    private static final IKey<String> 	BaseCls = 			newKey("baseCls", "btn");
    private static final IKey<String> 	GroupCls = 			newKey("groupCls", null);
    private static final IKey<String> 	Cls = 				newKey("cls", null);
    private static final IKey<String> 	OffCls = 			newKey("offCls", "btn-default");
    private static final IKey<String> 	OnCls = 			newKey("onCls", "btn-default");
    private static final IKey<String> 	OffActiveCls = 		newKey("offActiveCls", "btn-danger");
    private static final IKey<String> 	OnActiveCls = 		newKey("onActiveCls", "btn-success");
    private static final IKey<String> 	OffLabel= 			newKey("offLabel", "No");
    private static final IKey<String> 	OnLabel= 			newKey("onLabel", "Yes");
    private static final IKey<String> 	DisabledCursor = 	newKey("disabledCursor", "not-allowed");
    private static final IKey<Boolean>	Html = 				newKey("html", false);
    private static final IKey<String> 	IconCls= 			newKey("iconCls", "glyphicon");
    private static final IKey<IconType> OffIconCls = 		newKey("offIconCls", null);
    private static final IKey<IconType> OnIconCls = 		newKey("onIconCls", null);
    private static final IKey<String> 	OffTitle = 			newKey("offTitle", null);
    private static final IKey<String> 	OnTitle = 			newKey("onTitle", null);
    private static final IKey<Boolean> 	SwitchAlways = 		newKey("switchAlways", false);
    private static final IKey<Boolean> 	Reverse = 			newKey("reverse", false);
    private static final IKey<String[]> ToggleKeyCodes = 	newKey("toggleKeyCodes", new String[] {"13", "32"});
    private static final IKey<String> 	WarningMessage = 	newKey("warningMessage", "Please do not use bootstrap-checkbox element in label element.");

    /**
     * Default constructor
     */
    public BootstrapCheckBoxPickerConfig() {
    }
    
    public BootstrapCheckBoxPickerConfig withBaseGroupCls(final Orientation value) {
    	put(BaseGroupCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withBaseCls(final String value) {
    	put(BaseCls, value);
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withGroupCls(final ButtonGroup.Size value) {
    	put(GroupCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withCls(final String value) {
    	put(Cls, value);
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOffCls(final Type value) {
    	put(OffCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOnCls(final Type value) {
    	put(OnCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOffActiveCls(final Type value) {
    	put(OffActiveCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOnActiveCls(final Type value) {
    	put(OnActiveCls, cssClassOrNull(value));
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOffLabel(final String value) {
    	put(OffLabel, value);
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withOnLabel(final String value) {
    	put(OnLabel, value);
    	return this;
    }
    
    public BootstrapCheckBoxPickerConfig withDisabledCursor(final String value) {
        put(DisabledCursor, value);
        return this;
    }
    public BootstrapCheckBoxPickerConfig withHtml(final boolean value) {
    	put(Html, value);
    	return this;
    }
    public BootstrapCheckBoxPickerConfig withIconCls(final String value) {
    	put(IconCls, value);
    	return this;
    }
    public BootstrapCheckBoxPickerConfig withOffIconCls(final IconType value) {
    	put(OffIconCls, value);
    	return this;
    }
    public BootstrapCheckBoxPickerConfig withOnIconCls(final IconType value) {
    	put(OnIconCls, value);
    	return this;
    }

    public BootstrapCheckBoxPickerConfig withSwitchAlwaysOffTitle(final boolean value) {
        put(SwitchAlways, value);
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
    public BootstrapCheckBoxPickerConfig withOffTitle(final String value) {
    	put(OffTitle, value);
    	return this;
    }
    public BootstrapCheckBoxPickerConfig withOnTitle(final String value) {
    	put(OnTitle, value);
    	return this;
    }
    public BootstrapCheckBoxPickerConfig withWarningMessage(final String value) {
    	put(WarningMessage, value);
    	return this;
    }
    
    // ****************************************************
	// * Old v1.x methods, used for backwards compatibility
	// ****************************************************
    

    @Deprecated
    public BootstrapCheckBoxPickerConfig withDefaultClass(final String value) {
        put(Cls, value);
        return this;
    }

    @Deprecated
    public BootstrapCheckBoxPickerConfig withOffClass(final String value) {
        put(OffCls, value);
        return this;
    }

    @Deprecated
    public BootstrapCheckBoxPickerConfig withOnClass(final String value) {
        put(OnCls, value);
        return this;
    }

    @Deprecated
    public BootstrapCheckBoxPickerConfig withOffIcon(final IconType value) {
        return withOffIconCls(value);
    }

    @Deprecated
    public BootstrapCheckBoxPickerConfig withOnIcon(final IconType value) {
    	return withOnIconCls(value);
    }

    @Deprecated
    public BootstrapCheckBoxPickerConfig withStyle(final ButtonGroup.Size value) {
    	return withGroupCls(value);
    }

    private String cssClassOrNull(ICssClassNameProvider provider) {
    	if(provider == null)
    		return null;
    	
    	return provider.cssClassName();
    }
}
