package de.agilecoders.wicket.settings;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface ActiveThemeProvider {

    Theme getActiveTheme();

    void setActiveTheme(String themeName);

    void setActiveTheme(Theme theme);
}
