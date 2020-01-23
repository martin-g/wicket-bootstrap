package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A resource reference for <a href="https://github.com/aFarkas/html5shiv/">html5shiv.js</a>
 *
 * Used to add new HTML5 elements (which is simple code), but also supports printing HTML5
 * elements and includes the default styles for HTML5 elements, like block on article and section.
 */
public class Html5ShivJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final Html5ShivJavaScriptReference INSTANCE = new Html5ShivJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static Html5ShivJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private Html5ShivJavaScriptReference() {
        super(Html5ShivJavaScriptReference.class, "js/html5shiv.js");
    }

    /**
     * @return a JavaScript header item ready to be contributed
     */
    public static JavaScriptHeaderItem headerItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
