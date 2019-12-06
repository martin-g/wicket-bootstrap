package de.agilecoders.wicket.core.markup.html.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * Represents the modernizr js library
 *
 * @author miha
 */
public class ModernizrJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final ModernizrJavaScriptReference INSTANCE = new ModernizrJavaScriptReference();
    }

    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getModernizrResourceReference()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static ModernizrJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private ModernizrJavaScriptReference() {
        super("/modernizr/current/modernizr.min.js");
    }

}