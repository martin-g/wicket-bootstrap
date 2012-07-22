package de.agilecoders.wicket.settings;

import org.apache.wicket.Session;

import java.io.Serializable;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class SessionThemeProvider implements ActiveThemeProvider {
    private static final String KEY = "theme";
    private final ThemeProvider themeProvider;

    public SessionThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
    }

    @Override
    public Theme getActiveTheme() {
        Serializable value = Session.get().getAttribute(KEY);

        if (value != null) {
            return themeProvider.byName(String.valueOf(value));
        } else {
            return themeProvider.defaultTheme();
        }
    }

    @Override
    public void setActiveTheme(String themeName) {
        setActiveTheme(themeProvider.byName(themeName));
    }

    @Override
    public void setActiveTheme(Theme theme) {
        if (theme != null) {
            Session.get().setAttribute(KEY, theme.name());
        } else {
            Session.get().removeAttribute(KEY);
        }
    }
}
