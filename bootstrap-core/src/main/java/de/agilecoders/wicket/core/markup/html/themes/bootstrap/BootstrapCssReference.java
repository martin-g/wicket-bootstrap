package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

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
    private static final class Holder {
        private static final BootstrapCssReference INSTANCE = new BootstrapCssReference();
    }

    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getCssResourceReference()} ()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static BootstrapCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private BootstrapCssReference() {
        super("/bootstrap/current/css/bootstrap.css");
    }

}