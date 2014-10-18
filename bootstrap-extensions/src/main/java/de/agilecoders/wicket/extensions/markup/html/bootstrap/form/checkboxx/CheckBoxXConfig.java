package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkboxx;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

import org.apache.wicket.util.lang.Args;

/**
 * Configuration object for the settings of
 * <a href="https://github.com/kartik-v/bootstrap-checkbox-x">Checkbox X</a>
 */
public class CheckBoxXConfig extends AbstractConfig {

    public enum Sizes {
        xl, lg, md, sm, xs
    }

    /**
     * A flag whether to use three states for the checkbox. This will allow you to save null values,
     * in addition to the built in true and false values. Defaults to true. If set to false, it will allow only the default two values: true and false.
     */
    private static final IKey<Boolean> ThreeState = newKey("threeState", true);

    /**
     * A flag whether the checkboxes are to be styled in an inline-block with other elements placed
     * before or after. Defaults to true. If set to false, checkboxes will be displayed as a block (like in checkbox lists).
     */
    private static final IKey<Boolean> Inline = newKey("inline", true);

    /**
     * The HTML markup for the glyph / icon to be displayed when the checkbox is checked. Defaults to &lt;i class="glyphicon glyphicon-ok"&gt;&lt;/i&gt;
     */
    private static final IKey<String> IconChecked = newKey("iconChecked", "<i class=\"glyphicon glyphicon-ok\"></i>");

    /**
     * The HTML markup for the glyph / icon to be displayed when the checkbox is unchecked. Defaults to a blank character <em> </em>.
     */
    private static final IKey<String> IconUnchecked = newKey("iconUnchecked", " ");

    /**
     * The html markup for the glyph / icon to be displayed when the checkbox is null. Defaults to &lt;i class="glyphicon glyphicon-stop"&gt;&lt;/i&gt;
     */
    private static final IKey<String> IconNull = newKey("iconNull", "<i class=\"glyphicon glyphicon-stop\"></i>");

    /**
     * The size of the checkbox control. Should be one of xl (extra large), lg (large), md (medium), sm (small),
     * or xs (extra small). Defaults to md (medium).
     */
    private static final IKey<Sizes> Size = newKey("size", Sizes.md);

    /**
     * A flag indicating whether clicks on a wrapping &lt;label&gt; should fire change events
     */
    private static final IKey<Boolean> EnclosedLabel = newKey("enclosedLabel", false);

    /**
     * A flag indicating whether to display the native checkbox control instead of the advanced styled input generated
     * by the plugin. Defaults to false. This property will be applied only if your source element is a checkbox input.
     * Setting it to true will allow you to use a threeState native checkbox. Note that when you have set useNative to
     * true, you will lose the advanced styling features offered by bootstrap-checkbox-x.
     */
    private static final IKey<Boolean> UseNative = newKey("useNative", false);

    /**
     * Default constructor
     */
    public CheckBoxXConfig() {
    }

    /**
     * Copy constructor
     *
     * @param copy The
     */
    public CheckBoxXConfig(CheckBoxXConfig copy) {
        Args.notNull(copy, "copy");
        withThreeState(copy.get(ThreeState));
        withEnclosedLabel(copy.get(EnclosedLabel));
        withUseNative(copy.get(UseNative));
        withInline(copy.get(Inline));
        withIconChecked(copy.getString(IconChecked));
        withIconUnchecked(copy.getString(IconUnchecked));
        withIconNull(copy.getString(IconNull));
        withSize(copy.get(Size));
    }

    public CheckBoxXConfig withThreeState(final boolean value) {
        put(ThreeState, value);
        return this;
    }

    public CheckBoxXConfig withEnclosedLabel(final boolean value) {
        put(EnclosedLabel, value);
        return this;
    }

    public CheckBoxXConfig withUseNative(final boolean value) {
        put(UseNative, value);
        return this;
    }

    public CheckBoxXConfig withInline(final boolean value) {
        put(Inline, value);
        return this;
    }

    public CheckBoxXConfig withIconChecked(final String value) {
        put(IconChecked, value);
        return this;
    }

    public CheckBoxXConfig withIconUnchecked(final String value) {
        put(IconUnchecked, value);
        return this;
    }

    public CheckBoxXConfig withIconNull(final String value) {
        put(IconNull, value);
        return this;
    }

    public CheckBoxXConfig withSize(final Sizes value) {
        put(Size, value);
        return this;
    }
}
