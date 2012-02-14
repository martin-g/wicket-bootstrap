package de.agilecoders.wicket.markup.html;

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "bootstrap-js";
    
    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapJavaScriptReference();

    /**
     * Private constructor.
     */
    private BootstrapJavaScriptReference() {
        super(BootstrapJavaScriptReference.class, "bootstrap.min.js");
    }

}