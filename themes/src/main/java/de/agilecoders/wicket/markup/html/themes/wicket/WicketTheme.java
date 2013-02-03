package de.agilecoders.wicket.markup.html.themes.wicket;

import de.agilecoders.wicket.settings.Theme;

/**
 * Wicket theme.
 *
 * @author miha
 * @version 1.0
 */
public class WicketTheme extends Theme {

    /**
     * Construct.
     */
    public WicketTheme() {
        super("wicket", WicketThemeCssResourceReference.INSTANCE);
    }
}
