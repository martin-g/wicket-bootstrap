package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;

/**
 * {@link Bloodhound} configuration.
 *
 * @see "https://github.com/twitter/typeahead.js/blob/master/doc/bloodhound.md"
 */
public class BloodhoundConfig extends AbstractConfig {

    private static final IKey<Json.RawValue> DatumTokenizer = newKey("datumTokenizer", null);

    private static final IKey<Json.RawValue> QueryTokenizer = newKey("queryTokenizer", null);

    private static final IKey<Integer> Limit = newKey("limit", 5);

    private static final IKey<Json.RawValue> DupDetector= newKey("dupDetector", null);

    private static final IKey<Json.RawValue> Sorter = newKey("sorter", null);

    private static final IKey<Json.RawValue> Local = newKey("local", null);

    private static final IKey<Prefetch> Prefetch = newKey("prefetch", null);

    private static final IKey<Remote> Remote = newKey("remote", null);

    public BloodhoundConfig() {
        this(whitespaceDatumTokenizer("value"), queryWhitespaceTokenizer());
    }

    public BloodhoundConfig(Json.RawValue datumTokenizer, Json.RawValue queryTokenizer) {

        if (datumTokenizer == null) {
            throw new IllegalArgumentException("You need to specify a datumTokenizer");
        }

        if (queryTokenizer == null) {
            throw new IllegalArgumentException("You need to specify a queryTokenizer");
        }

        put(DatumTokenizer, datumTokenizer);
        put(QueryTokenizer, queryTokenizer);
    }

    /**
     * @param param the displayKey of suggestion objects
     * @return a default bloodhound datum-whitespace tokenizer
     */
    public static Json.RawValue whitespaceDatumTokenizer(String param) {
        return new Json.RawValue(
            String.format("function(d) { return Bloodhound.tokenizers.whitespace(d.%s); }", param));
    }

    /**
     * @return a default bloodhound query-whitespace tokenizer
     */
    public static Json.RawValue queryWhitespaceTokenizer() {
        return new Json.RawValue("Bloodhound.tokenizers.whitespace");
    }

    public BloodhoundConfig withLimit(final int limit) {
        put(Limit, limit);
        return this;
    }

    public BloodhoundConfig withDupDetector(final Json.RawValue detector) {
        put(DupDetector, detector);
        return this;
    }


    public BloodhoundConfig withSorter(final Json.RawValue sorter) {
        put(Sorter, sorter);
        return this;
    }


    public BloodhoundConfig withLocal(final Json.RawValue local) {
        put(Local, local);
        return this;
    }

    public BloodhoundConfig withPrefetch(final Prefetch prefetch) {
        put(Prefetch, prefetch);
        return this;
    }

    public BloodhoundConfig withRemote(final Remote remote) {
        put(Remote, remote);
        return this;
    }

    public Prefetch getPrefetch() {
        return get(Prefetch);
    }

    public Remote getRemote() {
        return get(Remote);
    }

    public Json.RawValue getLocal() {
        return get(Local);
    }
}
