package de.agilecoders.wicket.markup.html.bootstrap.block.prettyprint;

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class PrettyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "prettify-js";

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new PrettyJavaScriptReference();

    /**
     * Private constructor.
     */
    private PrettyJavaScriptReference() {
        super(PrettyJavaScriptReference.class, "prettify.js");
    }

}