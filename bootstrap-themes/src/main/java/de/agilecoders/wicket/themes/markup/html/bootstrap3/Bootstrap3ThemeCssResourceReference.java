package de.agilecoders.wicket.themes.markup.html.bootstrap3;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * bootstrap3 theme css resource reference
 *
 * @author miha
 */
public class Bootstrap3ThemeCssResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final Bootstrap3ThemeCssResourceReference INSTANCE = new Bootstrap3ThemeCssResourceReference();

    /**
     * Private constructor.
     */
    public Bootstrap3ThemeCssResourceReference() {
        super(Bootstrap3ThemeCssResourceReference.class, "css/bootstrap.css");
    }

}