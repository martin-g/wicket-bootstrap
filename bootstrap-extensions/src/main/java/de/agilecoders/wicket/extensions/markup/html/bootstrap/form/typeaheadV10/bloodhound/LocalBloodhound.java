package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import de.agilecoders.wicket.jquery.util.Json;

import java.util.Collections;
import java.util.List;

/**
 * A {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound}
 * implementation for local datasets.
 */
public class LocalBloodhound<T> extends Bloodhound<T> {

    public LocalBloodhound(String name, BloodhoundConfig config, List<String> datasource) {
        super(name, config);
        initConfig(datasource);
    }

    public LocalBloodhound(String name, List<String> datasource) {
        super(name);
        initConfig(datasource);
    }

    private void initConfig(List<String> datasource) {

        Function<String, String> createDatum = s -> {
            // create a { value: element } datum
            return String.format("{value: '%s'}", s);
        };

        String local = "[" + Joiner.on(',').join(Lists.transform(datasource, createDatum)) + "]";
        getConfig().withLocal(new Json.RawValue(local));
    }

    @Override
    public Iterable<T> getChoices(String input) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String renderChoice(T choice) {
        return choice != null ? choice.toString() : "";
    }
}
