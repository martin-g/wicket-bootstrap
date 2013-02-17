package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.settings.Theme;

/**
 * Bootstrap responsive theme.
 *
 * @author miha
 */
public class BootstrapResponsiveTheme extends Theme {

    /**
     * Construct.
     */
    public BootstrapResponsiveTheme() {
        super("bootstrap-responsive", BootstrapCssReference.INSTANCE, BootstrapResponsiveCssReference.INSTANCE);
    }
}
