package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * represents the prettify js library
 *
 * @author miha
 */
public class JQueryMigrateJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new JQueryMigrateJavaScriptReference();

    /**
     * Private constructor.
     */
    private JQueryMigrateJavaScriptReference() {
        super(JQueryMigrateJavaScriptReference.class, "js/jquery-migrate-1.2.1.js");
    }
}
