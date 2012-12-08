package de.agilecoders.wicket.markup.html.themes.wicket;

import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;

/**
 * Wicket theme css resource reference
 *
 * @author miha
 */
public class WicketThemeCssResourceReference extends BootstrapCssReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final WicketThemeCssResourceReference INSTANCE = new WicketThemeCssResourceReference();

    /**
     * Private constructor.
     */
    public WicketThemeCssResourceReference() {
        super(WicketThemeCssResourceReference.class, "css/bootstrap.wicket.less.css");
    }

}