package de.agilecoders.wicket.themes.markup.html.wicket;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * Wicket theme.
 *
 * @author miha
 */
public class WicketTheme extends Theme {

    /**
     * Construct.
     */
    public WicketTheme() {
        super("wicket", WicketThemeCssResourceReference.INSTANCE, BootstrapCssReference.instance());
    }
}
