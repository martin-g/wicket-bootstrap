package de.agilecoders.wicket.themes.markup.html.bootstrap3;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * bootstrap3 js resource reference
 *
 * @author miha
 */
public class Bootstrap3ThemeJsResourceReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final Bootstrap3ThemeJsResourceReference INSTANCE = new Bootstrap3ThemeJsResourceReference();

    /**
     * Private constructor.
     */
    public Bootstrap3ThemeJsResourceReference() {
        super(Bootstrap3ThemeJsResourceReference.class, "js/bootstrap.js");
    }

}