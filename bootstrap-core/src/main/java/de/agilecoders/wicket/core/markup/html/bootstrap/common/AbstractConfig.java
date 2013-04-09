package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import de.agilecoders.wicket.core.util.ConfigModel;
import de.agilecoders.wicket.core.util.Json;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.lang.Objects;

import java.util.HashMap;
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
        config = new HashMap<String, Object>();
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
        return new HashMap<String, Object>(config);
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
     * @param key   mandatory parameter
     * @param value mandatory parameter
     */
    protected final <T> AbstractConfig put(final IKey<T> key, final T value) {
        if (!key.isDefaultValue(value)) {
            config.put(key.key(), value);
        } else {
            remove(key);
        }
        return this;
    }

    /**
     * removes the given key (and its value) from configuration map.
     *
     * @param key the key to remove
     */
    protected final <T> T remove(final IKey<T> key) {
        return (T) config.remove(key.key());
    }

    /**
     * returns the value as string according to given key. If no value is set, the
     * default value will be returned.
     *
     * @param key The key to read.
     * @return the value as string.
     */
    protected final <T> String getString(final IKey<T> key) {
        final Object value = config.get(key.key());

        return String.valueOf(value != null ? value : key.getDefaultValue());
    }

    /**
     * Wraps IModel&lt;String&gt; in ConfigModel to serialize it as
     * simple String in the produced JSON.
     *
     * @param model The model to wrap.
     * @return A model that uses special Json serializer
     */
    protected ConfigModel wrap(IModel<String> model) {
        return new ConfigModel(model);
    }

    /**
     * Simple interface to enrich the {@link Map} key with some
     * functionality like assertion of value type and default value
     * handling.
     */
    public static interface IKey<T> {

        /**
         * @return the key to use in {@link Map}
         */
        public String key();

        /**
         * checks if given value is equal to default value
         *
         * @param value The value to check.
         * @return true, if given value is equal to default value
         */
        public boolean isDefaultValue(T value);

        /**
         * @return the default value
         */
        public T getDefaultValue();
    }

    /**
     * creates a new key.
     *
     * @param key          string representation of this key
     * @param defaultValue The default value
     */
    protected static <T> IKey<T> newKey(final String key, final T defaultValue) {
        return new Key<T>(key, defaultValue);
    }

    /**
     * Default {@link IKey} implementation
     */
    private static final class Key<T> implements IKey<T> {
        private final String key;
        private final T defaultValue;

        /**
         * Construct.
         *
         * @param key          string representation of this key
         * @param defaultValue The default value
         */
        private Key(final String key, final T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public boolean isDefaultValue(final T value) {
            return Objects.equal(value, defaultValue);
        }

        @Override
        public T getDefaultValue() {
            return defaultValue;
        }

    }
}
