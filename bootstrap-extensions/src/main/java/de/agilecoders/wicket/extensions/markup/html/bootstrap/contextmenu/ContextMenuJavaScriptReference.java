package de.agilecoders.wicket.extensions.markup.html.bootstrap.contextmenu;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * context menu jquery package
 *
 * @author miha
 */
public class ContextMenuJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final ContextMenuJavaScriptReference INSTANCE = new ContextMenuJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static ContextMenuJavaScriptReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private ContextMenuJavaScriptReference() {
        super(ContextMenuJavaScriptReference.class, "js/bootstrap-contextmenu.js");
    }
}