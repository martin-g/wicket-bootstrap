package de.agilecoders.wicket.markup.html.bootstrap.components;

import com.google.common.base.Preconditions;
import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.util.time.Duration;

/**
 * {@link PopoverBehavior} configuration
 *
 * @author miha
 */
public class PopoverConfig extends TooltipConfig {

    private enum Key implements IKey {
        /**
         * how to position the tooltip - top | bottom | left | right
         */
        Placement("placement", String.class, "right"),

        /**
         * how tooltip is triggered - click | hover | focus | manual
         */
        Trigger("trigger", String.class, "click");

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

    @Override
    public PopoverConfig withPlacement(final IPlacement value) {
        put(Key.Placement, value.value());
        return this;
    }

    @Override
    public PopoverConfig withTrigger(final Trigger value) {
        put(Key.Trigger, value.name());
        return this;
    }

    @Override
    public PopoverConfig withAnimation(boolean value) {
        return (PopoverConfig) super.withAnimation(value);
    }

    @Override
    public PopoverConfig withSelector(String value) {
        return (PopoverConfig) super.withSelector(value);
    }

    @Override
    public PopoverConfig withTitle(String value) {
        return (PopoverConfig) super.withTitle(value);
    }

    @Override
    public PopoverConfig withContent(String value) {
        return (PopoverConfig) super.withContent(value);
    }

    @Override
    public PopoverConfig withDelay(Duration value) {
        return (PopoverConfig) super.withDelay(value);
    }

    @Override
    public PopoverConfig withHtml(boolean value) {
        return (PopoverConfig) super.withHtml(value);
    }
}
