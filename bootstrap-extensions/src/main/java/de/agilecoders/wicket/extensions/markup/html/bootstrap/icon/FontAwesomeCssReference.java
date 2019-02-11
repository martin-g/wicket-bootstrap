package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * reference for font awesome css
 *
 * @deprecated use {@link FontAwesome5CssReference} if possible
 */
@Deprecated
public class FontAwesomeCssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final FontAwesomeCssReference INSTANCE = new FontAwesomeCssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesomeCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private FontAwesomeCssReference() {
        super("font-awesome/current/css/font-awesome.css");
    }
}
