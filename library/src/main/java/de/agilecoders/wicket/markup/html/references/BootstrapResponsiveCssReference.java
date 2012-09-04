package de.agilecoders.wicket.markup.html.references;

import de.agilecoders.wicket.util.References;
import org.apache.wicket.request.resource.CssResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResponsiveCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final CssResourceReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super(BootstrapResponsiveCssReference.class, "css/bootstrap-responsive.css");
    }

}