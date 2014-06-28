package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A resource reference for <a href="https://github.com/scottjehl/Respond">respond.js</a>
 * Used to polyfill for min/max-width CSS3 Media Queries (for IE 6-8, and more)
 */
public class RespondJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final RespondJavaScriptReference INSTANCE = new RespondJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static RespondJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private RespondJavaScriptReference() {
        super(RespondJavaScriptReference.class, "js/respond.js");
    }

    /**
     * @return a JavaScript header item ready to be contributed
     */
    public static JavaScriptHeaderItem headerItem() {
        return JavaScriptHeaderItem.forReference(instance(), null, null, false, null, "lt IE 9");
    }
}
