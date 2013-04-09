package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * reference for open web icon css
 *
 * @author miha
 */
public class OpenWebIconsCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final OpenWebIconsCssReference INSTANCE = new OpenWebIconsCssReference();


    /**
     * @return the single instance of the resource reference
     */
    public static OpenWebIconsCssReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private OpenWebIconsCssReference() {
        super(OpenWebIconsCssReference.class, "css/openwebicons-bootstrap.css");
    }
}