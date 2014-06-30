package de.agilecoders.wicket.themes.markup.html.google;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;

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
        super(name, BootstrapCssReference.instance(), GoogleCssReference.instance());
    }

    /**
     * Construct.
     */
    public GoogleTheme() {
        this("google");
    }

}
