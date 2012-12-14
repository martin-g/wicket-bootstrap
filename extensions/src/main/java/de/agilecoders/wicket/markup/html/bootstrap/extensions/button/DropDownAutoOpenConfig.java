package de.agilecoders.wicket.markup.html.bootstrap.extensions.button;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.util.time.Duration;

/**
 * {@link DropDownAutoOpen} configuration
 *
 * @author miha
 */
public class DropDownAutoOpenConfig extends AbstractConfig {

    /**
     * Holds all dropdown auto open configuration keys.
     */
    private enum Key implements IKey {

        /**
         * menu open delay
         */
        Delay("delay", Long.class, 500),

        /**
         * whether to close other menus or not
         */
        InstantlyCloseOthers("instantlyCloseOthers", Boolean.class, true);

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
     * Construct.
     */
    public DropDownAutoOpenConfig() {
        super();
    }

    /**
     * open delay
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public DropDownAutoOpenConfig withDelay(final Duration value) {
        put(Key.Delay, value.getMilliseconds());
        return this;
    }

    /**
     * whether to close other menus or not
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public DropDownAutoOpenConfig instantlyCloseOthers(final boolean value) {
        put(Key.InstantlyCloseOthers, value);
        return this;
    }

}
