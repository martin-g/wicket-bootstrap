package de.agilecoders.wicket.core.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Args;

/**
 *
 */
public class Dataset extends AbstractConfig {

    private static final IKey<String> Name = newKey("name", null);

    private static final IKey<String> ValueKey = newKey("valueKey", "value");

    private static final IKey<Integer> Limit = newKey("limit", 5);

    private static final IKey<String> Template = newKey("template", null);

    private static final IKey<Remote> Remote = newKey("remote", null);

    public Dataset(String name) {
        withName(name);
    }

    /**
     * The string used to identify the dataset. Used by typeahead.js to cache intelligently.
     *
     * @param name mandatory parameter
     * @return this instance for chaining
     */
    public Dataset withName(final String name) {
        Args.notEmpty(name, "name");
        put(Name, name);
        return this;
    }

    /**
     * The key used to access the value of the datum in the datum object. Defaults to <em>value</em>.
     *
     * @param valueKey mandatory parameter
     * @return this instance for chaining
     */
    public Dataset withValueKey(final String valueKey) {
        Args.notEmpty(valueKey, "valueKey");
        put(ValueKey, valueKey);
        return this;
    }

    /**
     * The key used to access the value of the datum in the datum object. Defaults to <em>value</em>.
     *
     * @param limit mandatory parameter
     * @return this instance for chaining
     */
    public Dataset withLimit(final int limit) {
        put(Limit, limit);
        return this;
    }

    /**
     * The template used to render suggestions. Can be a string or a precompiled template.
     * If not provided, suggestions will render as their value contained in a <p> element (i.e. <p>value</p>).
     *
     * @param template mandatory parameter
     * @return this instance for chaining
     */
    public Dataset withTemplate(final String template) {
        Args.notEmpty(template, "template");
        put(Template, template);
        return this;
    }

    /**
     * Can be a URL to fetch suggestions from when the data provided by local and prefetch is insufficient or,
     * if more configurability is needed, a <a href="https://github.com/twitter/typeahead.js#remote">remote options object</a>.
     *
     * @param remote mandatory parameter
     * @return this instance for chaining
     */
    public Dataset withRemote(final Remote remote) {
        Args.notNull(remote, "remote");
        put(Remote, remote);
        return this;
    }

    public Remote getRemote() {
        return get(Remote);
    }
}
