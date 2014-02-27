package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * represents the jquery backwards compatibility js library
 *
 * @author miha
 */
public class JQueryMigrateJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryMigrateJavaScriptReference INSTANCE = new JQueryMigrateJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryMigrateJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private JQueryMigrateJavaScriptReference() {
        super(JQueryMigrateJavaScriptReference.class, "js/jquery-migrate-1.2.1.js");
    }
}
