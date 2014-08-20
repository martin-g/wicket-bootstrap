package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import de.agilecoders.wicket.jquery.util.Json;

/**
 * Interface for a source of a <a href="https://github.com/twitter/typeahead.js/blob/master/doc/jquery_typeahead.md#datasets">Dataset</a>
 */
public interface Source<T> {

    /**
     * Get the javascript function for this source.
     *
     * @return the javascript function used as the source of a {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.DataSet}
     */
    Json.RawValue getFunction();

    /**
     * Get an iterable of choices for the given input term.
     */
    abstract Iterable<T> getChoices(String input);

    /**
     * Render the given choice to a Javascript object, e.g
     *
     * <p> Json.stringify(choice); </p>
     *
     * @return the javascript object representation of the given choice
     */
    String renderChoice(T choice);

}
