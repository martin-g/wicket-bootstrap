package de.agilecoders.wicket.markup.html.bootstrap.common;

import com.google.common.collect.Maps;
import de.agilecoders.wicket.util.Json;
import org.apache.wicket.util.io.IClusterable;

import java.util.Map;

/**
 * Base configuration class.
 *
 * @author miha
 */
public abstract class AbstractConfig implements IClusterable {

    private final Map<String, Object> config;

    /**
     * Construct.
     */
    protected AbstractConfig() {
        config = Maps.newHashMap();
    }

    /**
     * @return current configuration as json string
     */
    public final String toJsonString() {
        return Json.stringify(config);
    }

    /**
     * @return a copy of all configurations
     */
    public final Map<String, Object> all() {
        return Maps.newHashMap(config);
    }

    /**
     * @return true, if no special configuration is set.
     */
    public final boolean isEmpty() {
        return config.isEmpty();
    }

    /**
     * puts a new config to the configuration map. If given value is default
     * value of key then it will be removed. This is necessary to keep the ui
     * code small and clean. Also the given value type will be asserted by
     * {@link IKey} implementation.
     *
     * @param key mandatory parameter
     * @param value mandatory parameter
     */
    protected final void put(final IKey key, final Object value) {
        key.assertCorrectType(value);

        if (!key.isDefaultValue(value)) {
            config.put(key.key(), value);
        } else {
            remove(key);
        }
    }

    /**
     * removes the given key (and its value) from configuration map.
     *
     * @param key the key to remove
     */
    protected final void remove(final IKey key) {
        config.remove(key.key());
    }

    /**
     * returns the value as string according to given key. If no value is set, the
     * default value will be returned.
     *
     * @param key The key to read.
     * @return the value as string.
     */
    protected final String getString(final IKey key) {
        final Object value = config.get(key.key());

        return String.valueOf(value != null ? value : key.getDefaultValue());
    }

    /**
     * Simple interface to enrich the {@link Map} key with some
     * functionality like assertion of value type and default value
     * handling.
     */
    protected static interface IKey {

        /**
         * @return the key to use in {@link Map}
         */
        String key();

        /**
         * asserts the type of given value. The implementation of this method
         * should throw an exception if given value type doesn't match the
         * configured one.
         *
         * @param value The value to check
         */
        void assertCorrectType(Object value);

        /**
         * checks if given value is equal to default value
         *
         * @param value The value to check.
         * @return true, if given value is equal to default value
         */
        boolean isDefaultValue(Object value);

        /**
         * @return the default value
         */
        Object getDefaultValue();
    }
}
