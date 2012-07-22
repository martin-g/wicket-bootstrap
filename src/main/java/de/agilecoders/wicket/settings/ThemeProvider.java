package de.agilecoders.wicket.settings;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface ThemeProvider {

    /**
     * returns a theme by its name. If
     *
     * @param name The name of the theme
     * @return the theme according to given name
     */
    Theme byName(final String name);

    /**
     * @return a list of all available themes
     */
    List<Theme> available();

    /**
     * @return the default theme
     */
    Theme defaultTheme();
}
