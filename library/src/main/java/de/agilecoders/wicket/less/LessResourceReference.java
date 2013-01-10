package de.agilecoders.wicket.less;

import de.agilecoders.wicket.Bootstrap;
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
     * special variation which triggers the {@link LessResourceStreamLocator} to compile
     * the less files.
     */
    public static final String VARIATION = "less";

    /**
     * Construct.
     *
     * @param scope  mandatory parameter
     * @param name   mandatory parameter
     * @param locale resource locale
     * @param style  resource style
     */
    public LessResourceReference(final Class<?> scope, final String name, final Locale locale, final String style) {
        super(scope, name, locale, style, VARIATION);
    }

    /**
     * Construct.
     *
     * @param scope mandatory parameter
     * @param name  mandatory parameter
     */
    public LessResourceReference(final Class<?> scope, final String name) {
        this(scope, name, null, null);
    }

    /**
     * use fixed variation value to trigger {@link LessResourceStreamLocator}
     *
     * @return static string {@link LessResourceReference#VARIATION} if less is active
     */
    @Override
    public final String getVariation() {
        return Bootstrap.getSettings().getBootstrapLessCompilerSettings().useLessCompiler() ? VARIATION : null;
    }
}
