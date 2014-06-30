package de.agilecoders.wicket.themes.markup.html.google;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * # Description
 *
 * java representation of TODC Bootstrap. This theme modifies bootstrap and therefor both
 * files gets loaded (`bootstrap.css` and `todc-bootstrap.css`).
 *
 * # Usage
 *
 * ```java
 * settings.setThemeProvider(new SingleThemeProvider(new GoogleTheme()));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class GoogleTheme extends Theme {

    /**
     * Construct.
     */
    public GoogleTheme(final String name) {
        super(name, BootstrapCssReference.instance(), GoogleCssReference.instance());
    }

    /**
     * Construct.
     */
    public GoogleTheme() {
        this("google");
    }

}
