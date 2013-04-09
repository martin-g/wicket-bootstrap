package de.agilecoders.wicket.core.markup.html.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Represents the modernizr js library
 *
 * @author miha
 */
public class ModernizrJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new ModernizrJavaScriptReference();

    /**
     * Private constructor.
     */
    private ModernizrJavaScriptReference() {
        super("/modernizr/current/modernizr.js");
    }

}