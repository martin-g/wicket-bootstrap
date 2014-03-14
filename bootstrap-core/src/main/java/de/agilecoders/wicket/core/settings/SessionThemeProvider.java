package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.Bootstrap;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.string.Strings;

/**
 * An {@link ActiveThemeProvider} implementation that stores the active theme
 * in the user session as style.
 *
 * @author miha
 */
public class SessionThemeProvider implements ActiveThemeProvider {

    @Override
    public ITheme getActiveTheme() {
        String style = Session.get().getStyle();

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
        Session session = Session.get();
        assertBoundSession(session);

        if (theme != null) {
            session.setStyle(theme.name());
        } else {
            session.setStyle(null);
        }
    }

    /**
     * checks on existing session, if there isn't one it will be created.
     * @param session
     */
    private void assertBoundSession(Session session) {
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
