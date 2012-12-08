package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.less.LessResourceReference;

/**
 * Responsive css reference
 *
 * @author miha
 */
public class BootstrapResponsiveCssReference extends LessResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapResponsiveCssReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super(BootstrapResponsiveCssReference.class, "css/responsive.less.css");
    }

    /**
     * Construct.
     *
     * @param scope The scope class
     * @param name  The name of the resource
     */
    protected BootstrapResponsiveCssReference(final Class<?> scope, final String name) {
        super(scope, name);
    }

}