package de.agilecoders.wicket.core.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Args;

/**
 *
 */
class Remote extends AbstractConfig {

    private static final IKey<CharSequence> Url = newKey("url", null);

    /**
     * A URL to make requests to when when the data provided by local and prefetch is insufficient.
     *
     * @param url mandatory parameter
     * @return this instance for chaining
     */
    public Remote withUrl(final CharSequence url) {
        Args.notEmpty(url, "url");
        put(Url, url + "&term=%QUERY");
        return this;
    }
}
