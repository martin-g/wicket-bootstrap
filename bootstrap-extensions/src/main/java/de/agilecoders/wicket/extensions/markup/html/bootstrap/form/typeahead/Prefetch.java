package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.util.Json;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.time.Duration;

/**
 * Configuration for Twitter Typeahead's
 * <a href="https://github.com/twitter/typeahead.js#prefetch">prefetch</a> setting.
 */
public class Prefetch extends AbstractConfig {

    private static final IKey<CharSequence> Url = newKey("url", null);

    private static final IKey<Long> Ttl = newKey("ttl", Duration.days(1).getMilliseconds());

    private static final IKey<Json.RawValue> Filter = newKey("filter", null);

    /**
     * A URL to a JSON file containing an array of datums. <strong>Required</strong>.
     *
     * @param url mandatory parameter
     * @return this instance for chaining
     */
    public Prefetch withUrl(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url);
        return this;
    }

    /**
     * The time (in milliseconds) the prefetched data should be cached in localStorage.
     * Defaults to 86400000 (1 day).
     *
     * @param ttl the time (in milliseconds) the prefetched data should be cached in localStorage.
     * @return this instance for chaining
     */
    public Prefetch withTtl(final long ttl) {
        put(Ttl, ttl);
        return this;
    }

    /**
     * A function with the signature filter(parsedResponse) that transforms the response body
     * into an array of datums. Expected to return an array of datums.
     *
     * @param filter A function with the signature filter(parsedResponse) that
     *               transforms the response body into an array of datums
     * @return this instance for chaining
     */
    public Prefetch withFilter(final Json.RawValue filter) {
        Args.notNull(filter, "filter");
        put(Filter, filter);
        return this;
    }
}
