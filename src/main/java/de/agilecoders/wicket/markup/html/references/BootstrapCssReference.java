package de.agilecoders.wicket.markup.html.references;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "bootstrap-css";

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapCssReference();

    /**
     * Private constructor.
     */
    private BootstrapCssReference() {
        super(BootstrapCssReference.class, "bootstrap.min.css");
    }

}