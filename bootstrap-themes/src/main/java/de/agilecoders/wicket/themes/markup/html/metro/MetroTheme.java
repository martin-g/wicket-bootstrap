package de.agilecoders.wicket.themes.markup.html.metro;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * Metro theme.
 *
 * @author miha
 * @version 1.0
 */
public class MetroTheme extends Theme {

    /**
     * Construct.
     */
    public MetroTheme(final String name) {
        super(name, MetroCssReference.instance(), BootstrapCssReference.instance());
    }

    /**
     * Construct.
     */
    public MetroTheme() {
        this("metro");
    }

}
