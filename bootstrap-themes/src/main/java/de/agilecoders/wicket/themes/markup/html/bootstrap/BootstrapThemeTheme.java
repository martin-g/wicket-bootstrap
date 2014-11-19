package de.agilecoders.wicket.themes.markup.html.bootstrap;

import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import java.util.Collections;
import java.util.List;

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
 *     settings.setThemeProvider(new SingleThemeProvider(new BootstrapThemeTheme()));
 *     Bootstrap.install(this, settings);
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
        super(name);
    }

    /**
     * Construct using default theme name: `bootstrap-theme`.
     */
    public BootstrapThemeTheme() {
        this("bootstrap-theme");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        HeaderItem headerItem = CssHeaderItem.forReference(BootstrapThemeThemeCssReference.instance()).setId(BOOTSTRAP_THEME_MARKUP_ID);
        return Collections.singletonList(headerItem);
    }
}
