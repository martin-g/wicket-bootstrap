package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.string.Strings;

/**
 * #### Description
 *
 * An {@link ActiveThemeProvider} implementation that stores the active theme
 * in the user session as style.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class SessionThemeProvider implements ActiveThemeProvider {

    @Override
    public ITheme getActiveTheme() {
        String style = loadThemeName();

        ThemeProvider themeProvider = themeProvider();

        if (Strings.isEmpty(style)) {
            return themeProvider.defaultTheme();
        } else {
            return themeProvider.byName(style);
        }
    }

    @Override
    public void setActiveTheme(String themeName) {
        ThemeProvider themeProvider = themeProvider();
        ITheme theme = themeProvider.byName(themeName);
        setActiveTheme(theme);
    }

    @Override
    public void setActiveTheme(ITheme theme) {
        if (theme != null) {
            storeThemeName(theme.name());
        } else {
            storeThemeName(null);
        }
    }

    /**
     * Loads the name of theme from the session's style
     *
     * @return The session's style
     */
    protected String loadThemeName() {
        return Session.get().getStyle();
    }

    /**
     * Stores the theme name in the session's style
     *
     * @param themeName The name of the current theme
     */
    protected void storeThemeName(String themeName) {
        Session session = Session.get();
        assertBoundSession(session);
        session.setStyle(themeName);
    }

    /**
     * checks on existing session, if there isn't one it will be created.
     *
     * @param session current session to bind
     */
    protected void assertBoundSession(Session session) {
        if (session.isTemporary()) {
            session.bind();
        }
    }

    /**
     * @return the {@link ThemeProvider} implementation
     */
    private ThemeProvider themeProvider() {
        if (Application.exists()) {
            return Bootstrap.getSettings().getThemeProvider();
        } else {
            throw new WicketRuntimeException("no application assigned to current thread");
        }
    }
}
