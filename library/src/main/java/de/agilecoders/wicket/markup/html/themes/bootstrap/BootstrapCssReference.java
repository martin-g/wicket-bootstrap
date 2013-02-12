package de.agilecoders.wicket.markup.html.themes.bootstrap;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * The default bootstrap css.
 *
 * @author miha
 */
public class BootstrapCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapCssReference INSTANCE = new BootstrapCssReference();

    /**
     * Private constructor.
     */
    private BootstrapCssReference() {
        super(BootstrapCssReference.class, "css/bootstrap.css");
    }

    /**
     * Construct.
     *
     * @param scope mandatory parameter
     * @param name  mandatory parameter
     */
    protected BootstrapCssReference(Class<?> scope, String name) {
        super(scope, name);
    }

}