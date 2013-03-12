package de.agilecoders.wicket.markup.html.themes.metro;

import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
import de.agilecoders.wicket.settings.Theme;

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
        super(name, MetroCssReference.instance(), BootstrapResponsiveCssReference.INSTANCE);
    }

    /**
     * Construct.
     */
    public MetroTheme() {
        this("metro");
    }

}
