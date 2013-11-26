package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;
import org.apache.wicket.util.lang.Args;

import java.util.Locale;

/**
 * Configuration for Twitter Typeahead's
 * <a href="https://github.com/twitter/typeahead.js#remote">remote</a> setting.
 */
public class Remote extends AbstractConfig {

    public static enum RateLimit {
        THROTTLE,

        DEBOUNCE
    }

    protected static final IKey<CharSequence> Url = newKey("url", null);

    protected static final IKey<String> Wildcard = newKey("wildcard", "%QUERY");

    protected static final IKey<String> RateLimit = newKey("rateLimitFn", "debounce");

    protected static final IKey<Json.RawValue> Filter = newKey("filter", null);

    protected static final IKey<Json.RawValue> BeforeSend = newKey("beforeSend", null);

    protected static final IKey<Integer> MaxParallelRequests = newKey("maxParallelRequests", 6);

    protected static final IKey<Json.RawValue> Replace = newKey("replace", null);

    /**
     * A URL to make requests to when when the data provided by local and prefetch is insufficient.
     * This setting is always set by Typeahead component.
     *
     * @param url mandatory parameter
     * @return this instance for chaining
     */
    public Remote withUrl(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url + "&term=" + get(Wildcard));
        return this;
    }

    /**
     * A URL to make requests to when when the data provided by local and prefetch is insufficient.
     *
     * @param wildcard mandatory parameter
     * @return this instance for chaining
     */
    public Remote withWildcard(final String wildcard) {
        Args.notEmpty(wildcard, "wildcard");
        put(Wildcard, wildcard);
        return this;
    }

    /**
     * A URL to make requests to when when the data provided by local and prefetch is insufficient.
     *
     * @param rateLimit mandatory parameter
     * @return this instance for chaining
     */
    public Remote withRateLimit(final RateLimit rateLimit) {
        Args.notNull(rateLimit, "rateLimit");
        put(RateLimit, rateLimit.name().toLowerCase(Locale.ENGLISH));
        return this;
    }

    /**
     * A function with the signature filter(parsedResponse) that transforms the response body
     * into an array of datums. Expected to return an array of datums.
     *
     * @param filter mandatory parameter
     * @return this instance for chaining
     */
    public Remote withFilter(final Json.RawValue filter) {
        Args.notNull(filter, "filter");
        put(Filter, filter);
        return this;
    }

    /**
     * A pre-request callback with the signature beforeSend(jqXhr, settings).
     * Can be used to set custom headers. See the jQuery.ajax docs for more info.
     *
     * @param beforeSend A pre-request callback that can
     *                   be used to set custom headers
     * @return this instance for chaining
     */
    public Remote withBeforeSend(final Json.RawValue beforeSend) {
        Args.notNull(beforeSend, "beforeSend");
        put(BeforeSend, beforeSend);
        return this;
    }

    /**
     * The max number of parallel requests typeahead.js can have pending. Defaults to 6.
     *
     * @param maxParallelRequests The max number of parallel requests
     *                            typeahead.js can have pending
     * @return this instance for chaining
     */
    public Remote withMaxParallelRequests(final int maxParallelRequests) {
        put(MaxParallelRequests, maxParallelRequests);
        return this;
    }

    /**
     * A function with the signature replace(url, uriEncodedQuery) that can be
     * used to override the request URL. Expected to return a valid URL.
     * If set, no wildcard substitution will be performed on url.
     *
     * @param replace A function that can be used to override the request URL.
     * @return this instance for chaining
     */
    public Remote withReplace(final Json.RawValue replace) {
        Args.notNull(replace, "replace");
        put(Replace, replace);
        return this;
    }
}
