package de.agilecoders.wicket.themes.markup.html.bootstrap;

import de.agilecoders.wicket.core.settings.Theme;

/**
 * #### Description
 *
 * java representation of `bootstrap-theme.css` which is an enhanced default bootstrap theme.
 *
 * #### Usage
 *
 * ```
 * public void init() {
 *     final IBootstrapSettings settings = new BootstrapSettings();
 *     Bootstrap.install(this, settings);
 *     // theme provider must be set after `install` because it needs webjars.
 *     settings.setThemeProvider(new SingleThemeProvider(new BootstrapThemeTheme()));
 * }
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class BootstrapThemeTheme extends Theme {

    /**
     * Constructor.
     *
     * @param name the theme name
     */
    public BootstrapThemeTheme(String name) {
        super(name, BootstrapThemeThemeCssReference.instance());
    }

    /**
     * Construct using default theme name: `bootstrap-theme`.
     */
    public BootstrapThemeTheme() {
        this("bootstrap-theme");
    }
}
