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
public class LessResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * special variation which triggers the {@link LessResourceStreamLocator} to compile
     * the less files.
     */
    @Deprecated
    public static final String VARIATION = "less";

    /**
     * Construct.
     *
     * @param scope mandatory parameter
     * @param name  mandatory parameter
     */
    public LessResourceReference(final Class<?> scope, final String name) {
        this(scope, name, null, null, null);
    }


    /**
     * Construct.
     *
     * @param key  mandatory parameter
     */
    public LessResourceReference(final Key key) {
        super(key);
    }

    /**
     * Construct.
     *
     * @param scope  mandatory parameter
     * @param name   mandatory parameter
     * @param locale resource locale
     * @param style  resource style
     */
    public LessResourceReference(final Class<?> scope, final String name, final Locale locale, final String style, final String variation) {
        super(scope, name, locale, style, variation);
    }

    @Override
    public LessPackageResource getResource() {
        return new LessPackageResource(getScope(), getName(), getLocale(), getStyle(), getVariation());
    }
}
