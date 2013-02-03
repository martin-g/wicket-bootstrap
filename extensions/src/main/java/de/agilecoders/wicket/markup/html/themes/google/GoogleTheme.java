package de.agilecoders.wicket.markup.html.themes.google;

import de.agilecoders.wicket.settings.Theme;

/**
 * Google theme.
 *
 * @author miha
 */
public class GoogleTheme extends Theme {

    /**
     * Construct.
     */
    public GoogleTheme(final String name) {
        super(name, GoogleCssReference.instance());
    }

    /**
     * Construct.
     */
    public GoogleTheme() {
        this("google");
    }

}
