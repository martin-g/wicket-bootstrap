package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;

/**
 * {@link Typeahead} configuration
 *
 * @author miha
 */
public class TypeaheadConfig extends AbstractConfig {

    /**
     * The data source to query against.
     */
    private static final IKey<IDataSource> Source = newKey("source", null);

    /**
     * The max number of items to display in the dropdown.
     */
    private static final IKey<Integer> Items = newKey("items", 8);

    /**
     * The minimum character length needed before triggering autocomplete suggestions
     */
    private static final IKey<Integer> MinLength = newKey("minLength", 1);

    /**
     * The max number of items to display in the dropdown.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withNumberOfItems(final int value) {
        put(Items, value);
        return this;
    }

    /**
     * The data source to query against.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withDataSource(final IDataSource<?> value) {
        put(Source, value);
        return this;
    }

    /**
     * The minimum character length needed before triggering autocomplete suggestions
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public TypeaheadConfig withMinLength(final int value) {
        put(MinLength, value);
        return this;
    }
}
