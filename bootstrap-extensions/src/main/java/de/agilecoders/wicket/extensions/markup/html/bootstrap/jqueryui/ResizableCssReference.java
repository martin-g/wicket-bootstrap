package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * resizable jquery css package
 *
 * @author miha
 */
public class ResizableCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final ResizableCssReference INSTANCE = new ResizableCssReference();


    /**
     * @return the single instance of the resource reference
     */
    public static ResizableCssReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private ResizableCssReference() {
        super(ResizableCssReference.class, "css/resizable.css");
    }
}