package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.util.lang.Args;

import java.util.Map;

/**
 * An {@link de.agilecoders.wicket.jquery.AbstractConfig} for a typeahead.js <a
 * href="https://github.com/twitter/typeahead.js/blob/master/doc/jquery_typeahead.md#datasets">DataSet</a>
 */
public class DataSet<T> extends AbstractConfig {

    private final Source<T> source;

    public DataSet(Source<T> source) {
        this.source = source;

        if (source instanceof Bloodhound) {
            Bloodhound bh = (Bloodhound) source;
            withName(bh.getName());
        }
    }

    private static final IKey<Json.RawValue> Source = newKey("source", null);

    private static final IKey<String> Name = newKey("name", null);

    private static final IKey<String> DisplayKey = newKey("displayKey", "value");

    private static final IKey<Map<String, Json.RawValue>> Templates = newKey("templates", null);


    public DataSet withName(final String name) {
        Args.notEmpty(name, "name");
        put(Name, name);
        return this;
    }

    public DataSet withDisplayKey(final String displayKey) {
        Args.notEmpty(displayKey, "displayKey");
        put(DisplayKey, displayKey);
        return this;
    }

    public DataSet withSource(final Source<T> source) {
        Args.notNull(source, "source");
        put(Source, source.getFunction());
        return this;
    }

    public DataSet withTemplates(final Map<String, Json.RawValue> templates) {
        Args.notNull(templates, "templates");
        put(Templates, templates);
        return this;
    }

    public Source<T> getSource() {
        return source;
    }
}
