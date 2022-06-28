package de.agilecoders.wicket.core.markup.html.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * represents the bootstrap.bundle js library
 */
public class BootstrapBundleJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final BootstrapBundleJavaScriptReference INSTANCE = new BootstrapBundleJavaScriptReference();
    }

    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getJsResourceReference()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static BootstrapBundleJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private BootstrapBundleJavaScriptReference() {
        super("/bootstrap/current/js/bootstrap.bundle.js");
    }
}
