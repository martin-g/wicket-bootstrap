package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import com.google.common.collect.ObjectArrays;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Basic configuration for <a href="https://github.com/twitter/typeahead.js/blob/master/doc/jquery_typeahead.md">typeahead.js</a>
 */
public class TypeaheadConfig<T> extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    private final DataSet<T>[] datasets;

    /** If set to true, the component will bind to the typeahead:selected event **/
    private boolean selectEvent = false;

    public TypeaheadConfig(DataSet<T> firstSet, DataSet<T> ... datasets) {

        if (firstSet == null || firstSet.getSource() == null) {
            throw new IllegalArgumentException(
                "No valid dataset provided. You need to set a least one valid dataset.");
        }

        if (datasets != null) {
            this.datasets = ObjectArrays.concat(firstSet, datasets);
        } else {
            this.datasets = new DataSet[]{firstSet};
        }
    }

    private static final IKey<Boolean> Highlight = newKey("highlight", Boolean.FALSE);

    private static final IKey<Boolean> Hint = newKey("hint", Boolean.TRUE);

    private static final IKey<Integer> MinLength = newKey("minLength", 1);

    public TypeaheadConfig<T> withHighlight(final boolean highlight) {
        put(Highlight, highlight);
        return this;
    }

    public TypeaheadConfig<T> withHint(final boolean hint) {
        put(Hint, hint);
        return this;
    }

    public TypeaheadConfig<T> withMinLength(final int minLength) {
        put(MinLength, minLength);
        return this;
    }

    public TypeaheadConfig<T> withSelectEvent(boolean withSelectEvent) {
        this.selectEvent = withSelectEvent;
        return this;
    }


    public boolean isSelectEvent() {
        return selectEvent;
    }

    public DataSet<T>[] getDatasets() {
        return datasets;
    }

    /**
     * Convenience method for creating a typeahead.js remote {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound}
     * configuration
     */
    public static <T> TypeaheadConfig<T> forRemote(Bloodhound<T> bloodhound) {
        return new TypeaheadConfig<>(new DataSet<>(bloodhound));
    }
}
