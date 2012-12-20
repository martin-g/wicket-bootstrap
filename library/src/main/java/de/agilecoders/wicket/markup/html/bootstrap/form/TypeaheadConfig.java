package de.agilecoders.wicket.markup.html.bootstrap.form;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Objects;

/**
 * {@link Typeahead} configuration
 *
 * @author miha
 */
public class TypeaheadConfig extends AbstractConfig {

    /**
     * configuration key definition
     */
    private enum Key implements IKey {

        /**
         * The data source to query against.
         */
        Source("source", IDataSource.class, null),

        /**
         * The max number of items to display in the dropdown.
         */
        Items("items", Integer.class, 8),

        /**
         * The minimum character length needed before triggering autocomplete suggestions
         */
        MinLength("minLength", Integer.class, 1);

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
     * The max number of items to display in the dropdown.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withNumberOfItems(final int value) {
        put(Key.Items, value);
        return this;
    }

    /**
     * The data source to query against.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withDataSource(final IDataSource<?> value) {
        put(Key.Source, value);
        return this;
    }

    /**
     * The minimum character length needed before triggering autocomplete suggestions
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withMinLength(final int value) {
        put(Key.MinLength, value);
        return this;
    }
}
