package de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * html5 player jquery package
 *
 * @author miha
 */
public class Html5PlayerJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final Html5PlayerJavaScriptReference INSTANCE = new Html5PlayerJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static Html5PlayerJavaScriptReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private Html5PlayerJavaScriptReference() {
        super(Html5PlayerJavaScriptReference.class, "js/html5-player.js");
    }
}