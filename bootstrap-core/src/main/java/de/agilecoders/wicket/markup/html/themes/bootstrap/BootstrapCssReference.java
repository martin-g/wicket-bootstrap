package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * The default bootstrap css.
 *
 * @author miha
 */
public class BootstrapCssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapCssReference INSTANCE = new BootstrapCssReference();

    /**
     * Private constructor.
     */
    private BootstrapCssReference() {
        super("/bootstrap/current/css/bootstrap.css");
    }

}