package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.util.lang.Args;

import java.io.IOException;

/**
 * See https://github.com/twitter/typeahead.js/blob/master/doc/bloodhound.md#remote
 */
@JsonSerialize(using = Remote.RemoteSerializer.class)
public class Remote extends AbstractConfig {

    @JsonSerialize(using = RateLimitBySerializer.class)
    public enum RateLimit {
        DEBOUNCE,
        THROTTLE
    }

    public static final String DEFAULT_WILDCARD = "%QUERY";

    private static final IKey<CharSequence> Url = newKey("url", null);

    private static final IKey<String> Wildcard = newKey("wildcard", DEFAULT_WILDCARD);

    private static final IKey<RateLimit> RateLimitBy = newKey("rateLimitBy", RateLimit.DEBOUNCE);

    private static final IKey<Integer> RateLimitWait= newKey("rateLimitWait", 300);

    private static final IKey<Json.RawValue> Filter = newKey("filter", null);

    private static final IKey<Json.RawValue> Replace = newKey("replace", null);

    private static final IKey<AbstractConfig> Ajax = newKey("ajax", null);

    public Remote(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url);
    }

    public Remote() {
    }

    public Remote withUrl(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url);
        return this;
    }

    public Remote withAjax(final AbstractConfig ajax) {
        Args.notNull(ajax, "ajax");
        put(Ajax, ajax);
        return this;
    }

    public Remote withFilter(final Json.RawValue filter) {
        Args.notNull(filter, "filter");
        put(Filter, filter);
        return this;
    }

    public Remote withReplace(final Json.RawValue replace) {
        Args.notNull(replace, "replace");
        put(Replace, replace);
        return this;
    }

    public Remote withRateLimitBy(final RateLimit limit) {
        put(RateLimitBy, limit);
        return this;
    }

    public Remote withRateLimitWait(final int wait) {
        put(RateLimitWait, wait);
        return this;
    }

    public Remote withWildcard(final String wildcard) {
        Args.notEmpty(wildcard, "wildcard");
        put(Wildcard, wildcard);
        return this;
    }

    /**
     * Check if this Remote instance has only a url configured.
     * @return
     */
    public boolean isSimple() {
        return all().size() == 1;
    }

    /**
     * Retrieve the wildcard expression.
     * @return
     */
    public String getWildcard() {
        return get(Wildcard);
    }

    public static Remote of(CharSequence url) {
        return new Remote(url);
    }

    public static class RemoteSerializer extends JsonSerializer<Remote> {

        @Override
        public void serialize(Remote remote, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

            if (remote.isSimple()) {
                jsonGenerator.writeString(remote.getString(Url));
            } else {
                jsonGenerator.writeRawValue(remote.toJsonString());
            }

        }
    }

    public static class RateLimitBySerializer extends JsonSerializer<RateLimit> {
        @Override
        public void serialize(Remote.RateLimit rateLimitBy, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
            jsonGenerator.writeString(rateLimitBy.name().toLowerCase());
        }
    }
}
