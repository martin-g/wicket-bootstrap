package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * Bootstrap theme.
 *
 * @author miha
 */
public class BootstrapTheme extends Theme {

    /**
     * Construct.
     *
     * @param settings the bootstrap settings
     */
    public BootstrapTheme(final IBootstrapSettings settings) {
        super("bootstrap", settings.getCssResourceReference());
    }

    /**
     * Construct.
     */
    @Deprecated
    public BootstrapTheme() {
        super("bootstrap", BootstrapCssReference.instance());
    }

}
