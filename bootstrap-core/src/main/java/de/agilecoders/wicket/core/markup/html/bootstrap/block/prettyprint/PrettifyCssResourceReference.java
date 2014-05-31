package de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Adds the prettify css resource to the document's head.
 *
 * @author miha
 * @version 1.0
 */
public class PrettifyCssResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new PrettifyCssResourceReference();

    /**
     * Private constructor.
     */
    private PrettifyCssResourceReference() {
        super(PrettifyCssResourceReference.class, "prettify.css");
    }

}
