package de.agilecoders.wicket.markup.html.bootstrap.block.prettyprint;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class PrettyCssResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "prettify-css";

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new PrettyCssResourceReference();

    /**
     * Private constructor.
     */
    private PrettyCssResourceReference() {
        super(PrettyCssResourceReference.class, "prettify.css");
    }

}
