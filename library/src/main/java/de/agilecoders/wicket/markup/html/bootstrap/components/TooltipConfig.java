package de.agilecoders.wicket.markup.html.bootstrap.components;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.util.time.Duration;

/**
 * {@link TooltipBehavior} configuration
 *
 * @author miha
 */
public class TooltipConfig extends AbstractConfig {

    /**
     * Holds all tooltip configuration keys.
     */
    private enum Key implements IKey {
        /**
         * apply a css fade transition to the tooltip
         */
        Animation("animation", Boolean.class, true),

        /**
         * how to position the tooltip - top | bottom | left | right
         */
        Placement("placement", String.class, "top"),

        /**
         * If a selector is provided, tooltip objects will be delegated to the specified targets.
         */
        Selector("selector", String.class, "false"),

        /**
         * default content value if `data-content` attribute isn't present
         */
        Content("content", String.class, ""),

        /**
         * default title value if `title` tag isn't present
         */
        Title("title", String.class, ""),

        /**
         * how tooltip is triggered - click | hover | focus | manual
         */
        Trigger("trigger", String.class, "hover"),

        /**
         * delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
         * <p/>
         * Not implemented:
         * If a number is supplied, delay is applied to both hide/show
         * Object structure is: delay: { show: 500, hide: 100 }
         */
        Delay("delay", Integer.class, 0),

        /**
         * Insert html into the tooltip. If false, jquery's text method
         * will be used to insert content into the dom. Use text if you're worried about XSS attacks.
         */
        Html("html", Boolean.class, false);

        private final String key;
        private final Class type;
        private final Object defaultValue;

        /**
         * Construct.
         *
         * @param key          string representation of this key
         * @param type         The object type
         * @param defaultValue The default value
         */
        private Key(final String key, final Class type, final Object defaultValue) {
            this.key = key;
            this.type = type;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void assertCorrectType(final Object value) {
            Preconditions.checkArgument(type.isInstance(value));
        }

        @Override
        public boolean isDefaultValue(final Object value) {
            return Objects.equal(value, defaultValue);
        }

        @Override
        public Object getDefaultValue() {
            return defaultValue;
        }
    }

    /**
     * holds all possible tooltip positions
     */
    public enum Placement implements IPlacement {
        top, bottom, right, left;

        @Override
        public String value() {
            return name();
        }
    }

    /**
     * interface for tooltip placement. can be used to inject functions.
     */
    public static interface IPlacement {
        String value();
    }

    /**
     * all possible trigger for tooltip
     */
    public enum Trigger {
        click, hover, focus, manual
    }

    /**
     * Construct.
     */
    public TooltipConfig() {
        super();
    }

    /**
     * apply a css fade transition to the tooltip
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withAnimation(final boolean value) {
        put(Key.Animation, value);
        return this;
    }

    /**
     *  how to position the tooltip - top | bottom | left | right
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withPlacement(final IPlacement value) {
        put(Key.Placement, value.value());
        return this;
    }

    /**
     * If a selector is provided, tooltip objects will be delegated to the specified targets.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withSelector(final String value) {
        put(Key.Selector, value);
        return this;
    }

    /**
     * default title value if `title` tag isn't present
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withTitle(final String value) {
        put(Key.Title, value);
        return this;
    }

    /**
     * default content value if `data-content` attribute isn't present
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withContent(final String value) {
        put(Key.Content, value);
        return this;
    }

    /**
     * how tooltip is triggered - click | hover | focus | manual
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withTrigger(final Trigger value) {
        put(Key.Trigger, value.name());
        return this;
    }

    /**
     * delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
     * <p/>
     * Not implemented:
     * If a number is supplied, delay is applied to both hide/show
     * Object structure is: delay: { show: 500, hide: 100 }
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withDelay(final Duration value) {
        put(Key.Delay, value.getMilliseconds());
        return this;
    }

    /**
     * Insert html into the tooltip. If false, jquery's text method
     * will be used to insert content into the dom. Use text if you're worried about XSS attacks.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withHtml(final boolean value) {
        put(Key.Html, value);
        return this;
    }

}
