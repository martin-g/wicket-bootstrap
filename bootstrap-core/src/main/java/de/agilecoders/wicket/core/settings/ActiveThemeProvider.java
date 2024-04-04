package de.agilecoders.wicket.core.settings;

/**
 * #### Description
 *
 * The {@code ActiveThemeProvider} interface is used to identify the active theme when rendering
 * web resources.
 */
public interface ActiveThemeProvider {

    /**
     * returns the current active theme (can be user/session scoped). If none is
     * set a default theme should be returned (implementation specific).
     * There is a session scoped implementation: {@code SessionThemeProvider}
     *
     * @return the current active theme
     */
    ITheme getActiveTheme();

    /**
     * sets the active theme by its name.
     *
     * @param themeName the theme name
     */
    void setActiveTheme(String themeName);

    /**
     * sets the active theme
     *
     * @param theme the theme to set
     */
    void setActiveTheme(ITheme theme);
}
