package de.agilecoders.wicket.less;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Static resource reference for css resources. The resources are filtered (stripped comments and
 * whitespace) if there is registered compressor.
 *
 * @author miha
 * @version 1.0
 * @see org.apache.wicket.settings.IResourceSettings#getCssCompressor()
 */
public abstract class LessResourceReference extends CssResourceReference implements LessCompilable {
    private static final long serialVersionUID = 1L;

    private final static ResourceLocator RESOURCE_LOCATOR = new ResourceLocator();

    /**
     * Construct.
     *
     * @param scope     mandatory parameter
     * @param name      mandatory parameter
     * @param locale    resource locale
     * @param style     resource style
     * @param variation resource variation
     */
    public LessResourceReference(Class<?> scope, String name, Locale locale, String style,
                                 String variation) {
        super(scope, name, locale, style, variation);
    }

    /**
     * Construct.
     *
     * @param scope mandatory parameter
     * @param name  mandatory parameter
     */
    public LessResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * @return The {@link ResourceLocator} instance to find resources inside the classpath.
     */
    protected ResourceLocator resourceLocator() {
        return RESOURCE_LOCATOR;
    }

    @Override
    public LessPackageResource getResource() {
        return new LessPackageResource(getScope(), getName(), getLocale(), getStyle(), getVariation(), this);
    }

    @Override
    public void storeCompiledLess(byte[] content) throws IOException {
        Resource resource = resourceLocator().findResource(getScope(), getName());

        if (resource != null) {
            OutputStream bufferedFileOutputStream = null;
            try {
                bufferedFileOutputStream = new BufferedOutputStream(new FileOutputStream(resource.toFile()));
                IOUtils.write(content, bufferedFileOutputStream);
            } finally {
                IOUtils.closeQuietly(bufferedFileOutputStream);
            }
        } else {
            throw new WicketRuntimeException("invalid out file: " + resource);
        }
    }

}
