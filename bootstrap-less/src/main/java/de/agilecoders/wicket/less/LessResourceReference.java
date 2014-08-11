package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.CssResourceReference;

import java.util.Locale;

/**
 * A resource reference for <a href="http://lesscss.org">LESS</a> resources.
 * The resources are filtered (stripped comments and whitespace) if there is registered compressor.
 *
 * <p>Supported path schemes for LESS {@code @import} directive are:
 *
 * <ol>
 *   <li>Direct e.g. {@code @import "child.less";} the imported file must be in the same package and JAR.</li>
 *   <li>Absolute classpath e.g. {@code @import "classpath!/com/soluvas/web/child.less";} the imported file can be in any JAR but must be in specified package.</li>
 *   <li>Relative classpath e.g. {@code @import "package!child.less";} the imported file can be in any JAR but must be accessible relative to the {@code scope} given to {@code LessResourceReference}.</li>
 *   <li>WebJar e.g. {@code @import "webjars!bootstrap/current/less/variables.less";} (current version) or {@code &commat;import "webjars!bootstrap/3.2.0/less/variables.less";} (specific version).</li>
 * </ol>
 *
 * @author miha
 * @see org.apache.wicket.settings.ResourceSettings#getCssCompressor()
 */
public class LessResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

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
