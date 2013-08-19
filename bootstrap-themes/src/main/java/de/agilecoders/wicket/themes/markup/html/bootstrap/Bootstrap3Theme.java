package de.agilecoders.wicket.themes.markup.html.bootstrap;

import de.agilecoders.wicket.core.settings.Theme;

/**
 * Google theme.
 *
 * @author miha
 */
public class Bootstrap3Theme extends Theme {

    /**
     * Construct.
     */
    public Bootstrap3Theme(final String name) {
        super(name, Bootstrap3ThemeCssReference.instance(), Bootstrap3ThemeCssReference.instance());
    }

    /**
     * Construct.
     */
    public Bootstrap3Theme() {
        this("bootstrap3");
    }

}
