package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstraptoggle;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Configuration object for the settings of
 * <a href="http://www.bootstraptoggle.com/">Bootstrap Toggle</a>
 */
public class BootstrapToggleConfig extends AbstractConfig {

    public enum Size {
        large, normal, small, mini
    }

    public enum Style {
        /*default, */primary, success, info, warning, danger
    }

    private static final IKey<String> _Style = newKey("style", null);
    private static final IKey<String> OnLabel = newKey("on", null);
    private static final IKey<String> OffLabel = newKey("off", null);
    private static final IKey<Style> OnStyle = newKey("onstyle", Style.primary);
    private static final IKey<Style> OffStyle = newKey("offstyle", null);
    private static final IKey<Size> _Size = newKey("size", Size.normal);
    private static final IKey<Integer> Width = newKey("width", null);
    private static final IKey<Integer> Height = newKey("height", null);

    /**
     * Default constructor
     */
    public BootstrapToggleConfig() {
    }

    /**
     * Sets the <em>off</em> label of the toggle.
     *
     * Use {@link BootstrapToggle#getOffLabel()} for better i18n support
     *
     * @param value
     * @return this instance, for method chaining
     */
    protected BootstrapToggleConfig withOffLabel(String value) {
        put(OffLabel, value);
        return this;
    }

    /**
     * Sets the <em>on</em> label of the toggle.
     *
     * Use {@link BootstrapToggle#getOnLabel()} for better i18n support
     *
     * @param value
     * @return this instance, for method chaining
     */
    protected BootstrapToggleConfig withOnLabel(String value) {
        put(OnLabel, value);
        return this;
    }

    public BootstrapToggleConfig withStyle(final String value) {
        put(_Style, value);
        return this;
    }

    public BootstrapToggleConfig withOffStyle(final Style value) {
        put(OffStyle, value);
        return this;
    }

    public BootstrapToggleConfig withOnStyle(final Style value) {
        put(OnStyle, value);
        return this;
    }

    public BootstrapToggleConfig withSize(final Size value) {
        put(_Size, value);
        return this;
    }

    public BootstrapToggleConfig withWidth(final Integer value) {
        put(Width, value);
        return this;
    }

    public BootstrapToggleConfig withHeight(final Integer value) {
        put(Height, value);
        return this;
    }
}
