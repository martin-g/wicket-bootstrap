package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * #### Description
 *
 * Bootstrap theme that uses the css resource reference from bootstrap settings.
 *
 * #### Caution
 *
 * There's a constructor which is deprecated and will be removed before 1.0 is released. Please
 * use {@link de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapTheme#BootstrapTheme(de.agilecoders.wicket.core.settings.IBootstrapSettings)}
 * instead.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
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
