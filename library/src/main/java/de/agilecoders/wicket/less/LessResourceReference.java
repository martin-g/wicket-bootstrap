package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.CssResourceReference;

import java.util.Locale;

/**
 * Static resource reference for css resources. The resources are filtered (stripped comments and
 * whitespace) if there is registered compressor.
 *
 * @author miha
 * @see org.apache.wicket.settings.IResourceSettings#getCssCompressor()
 */
public abstract class LessResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param scope     mandatory parameter
     * @param name      mandatory parameter
     * @param locale    resource locale
     * @param style     resource style
     * @param variation resource variation
     */
    public LessResourceReference(final Class<?> scope, final String name, final Locale locale, final String style, final String variation) {
        super(scope, name, locale, style, variation);
    }

    /**
     * Construct.
     *
     * @param scope mandatory parameter
     * @param name  mandatory parameter
     */
    public LessResourceReference(final Class<?> scope, final String name) {
        this(scope, name, null, null, null);
    }

}
