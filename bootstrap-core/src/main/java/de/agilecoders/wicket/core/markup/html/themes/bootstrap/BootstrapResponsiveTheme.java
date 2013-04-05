package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.Theme;

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
