package de.agilecoders.wicket.themes.markup.html.wicket;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Wicket theme css resource reference
 *
 * @author miha
 */
public class WicketThemeCssResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final WicketThemeCssResourceReference INSTANCE = new WicketThemeCssResourceReference();

    /**
     * Private constructor.
     */
    public WicketThemeCssResourceReference() {
        super(WicketThemeCssResourceReference.class, "css/bootstrap.wicket.css");
    }

}