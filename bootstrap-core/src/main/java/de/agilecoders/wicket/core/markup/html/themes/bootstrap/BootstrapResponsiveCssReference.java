package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * Responsive css reference
 *
 * @author miha
 */
public class BootstrapResponsiveCssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapResponsiveCssReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super("/bootstrap/current/css/bootstrap-responsive.css");
    }
}