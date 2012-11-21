package de.agilecoders.wicket.markup.html.bootstrap.common;

import com.google.common.collect.Maps;
import de.agilecoders.wicket.util.Json;
import org.apache.wicket.util.io.IClusterable;

import java.util.Map;

/**
 * Base configuration class.
 *
 * @author miha
 * @version 1.0
 */
public abstract class AbstractConfig implements IClusterable {

    private final Map<String, Object> config;

    protected AbstractConfig() {
        config = Maps.newHashMap();
    }

    public final String toJsonString() {
        return Json.stringify(config);
    }

    public final Map<String, Object> all() {
        return Maps.newHashMap(config);
    }

    protected final void put(final IKey key, final Object value) {
        key.assertCorrectType(value);

        if (!key.isDefaultValue(value)) {
            config.put(key.key(), value);
        } else {
            remove(key);
        }
    }

    protected final void remove(final IKey key) {
        config.remove(key.key());
    }

    protected final String getString(final IKey key) {
        final Object value = config.get(key.key());

        return String.valueOf(value != null ? value : key.getDefaultValue());
    }

    protected static interface IKey {
        String key();

        void assertCorrectType(Object value);

        boolean isDefaultValue(Object value);

        Object getDefaultValue();
    }
}
