package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import de.agilecoders.wicket.jquery.util.Json;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        String local = datasource.stream().map(ds ->
            String.format("{value: '%s'}", ds)
        ).collect(Collectors.joining(",", "[", "]"));
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
