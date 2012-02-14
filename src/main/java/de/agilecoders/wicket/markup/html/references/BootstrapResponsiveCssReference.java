package de.agilecoders.wicket.markup.html.references;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResponsiveCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "bootstrap-responsive-css";

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super(BootstrapResponsiveCssReference.class, "bootstrap-responsive.min.css");
    }

}