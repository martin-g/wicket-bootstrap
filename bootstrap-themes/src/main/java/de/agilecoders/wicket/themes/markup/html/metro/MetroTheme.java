package de.agilecoders.wicket.themes.markup.html.metro;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
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
        super(name, MetroCssReference.instance(), BootstrapResponsiveCssReference.INSTANCE);
    }

    /**
     * Construct.
     */
    public MetroTheme() {
        this("metro");
    }

}
