package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.util.lang.Args;

/**
 * @see "https://github.com/twitter/typeahead.js/blob/master/doc/bloodhound.md#prefetch"
 */
public class Prefetch extends AbstractConfig {

    private static final IKey<CharSequence> Url = newKey("url", null);

    private static final IKey<CharSequence> CacheKey = newKey("cacheKey", null);

    private static final IKey<Integer> Ttl = newKey("ttl", 86400000);

    private static final IKey<CharSequence> Thumbprint = newKey("thumbprint", null);

    private static final IKey<Json.RawValue> Filter = newKey("filter", null);

    private static final IKey<AbstractConfig> Ajax = newKey("ajax", null);

    public Prefetch(CharSequence prefetchUrl) {
        Args.notEmpty(prefetchUrl, "prefetchUrl");
        put(Url, prefetchUrl);
        put(CacheKey, prefetchUrl);
    }

    public Prefetch withUrl(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url);
        return this;
    }

    public Prefetch withCacheKey(final CharSequence cacheKey) {
        Args.notEmpty(cacheKey, "cacheKey");
        put(CacheKey, cacheKey);
        return this;
    }

    public Prefetch withTtl(final int ttl) {
        put(Ttl, ttl);
        return this;
    }

    public Prefetch withThumbprint(final CharSequence thumbprint) {
        Args.notEmpty(thumbprint, "thumbprint");
        put(Thumbprint, thumbprint);
        return this;
    }

    public Prefetch withFilter(final Json.RawValue filter) {
        Args.notNull(filter, "filter");
        put(Filter, filter);
        return this;
    }

    public Prefetch withAjax(final AbstractConfig ajax) {
        Args.notNull(ajax, "ajax");
        put(Ajax, ajax);
        return this;
    }

}
