package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * reference for font awesome 5.x css
 */
public class FontAwesome5CssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final FontAwesome5CssReference INSTANCE = new FontAwesome5CssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesome5CssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private FontAwesome5CssReference() {
        super("font-awesome/current/css/all.css");
    }
}
