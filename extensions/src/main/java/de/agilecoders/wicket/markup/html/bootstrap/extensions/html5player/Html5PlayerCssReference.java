package de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * html5 player css package
 *
 * @author miha
 */
public class Html5PlayerCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final Html5PlayerCssReference INSTANCE = new Html5PlayerCssReference();


    /**
     * @return the single instance of the resource reference
     */
    public static Html5PlayerCssReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private Html5PlayerCssReference() {
        super(Html5PlayerCssReference.class, "css/html5-player.css");
    }
}