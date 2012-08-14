package de.agilecoders.wicket.markup.html.references;

import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapPrettifyCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapPrettifyCssReference();

    /**
     * Private constructor.
     */
    private BootstrapPrettifyCssReference() {
        super(BootstrapCssReference.class, "css/prettify.css");
    }

}