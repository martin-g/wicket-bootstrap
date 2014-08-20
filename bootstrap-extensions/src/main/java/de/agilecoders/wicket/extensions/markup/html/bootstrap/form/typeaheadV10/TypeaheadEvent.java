package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import com.fasterxml.jackson.databind.JsonNode;

import org.apache.wicket.util.io.IClusterable;

/**
 * Will be sent if the {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.TypeaheadConfig}s
 * selectEvent is set to true.
 */
public class TypeaheadEvent implements IClusterable {

    private final Type type;

    private final JsonNode datum;

    /**
     * Currently only SELECTED is supported.
     */
    public enum Type {
        OPENED,
        SELECTED,
        CLOSED
    }

    /**
     * @param type
     * @param datum the typeahead.js datum associated with the event
     */
    public TypeaheadEvent(Type type, JsonNode datum) {
        this.type = type;
        this.datum = datum;
    }

    public JsonNode getDatum() {
        return datum;
    }

    public Type getType() {
        return type;
    }
}
