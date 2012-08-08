package de.agilecoders.wicket.settings;

import com.google.common.base.Strings;
import org.apache.wicket.Session;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class SessionThemeProvider implements ActiveThemeProvider {

    private ThemeProvider themeProvider;

    public SessionThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
    }

    void setThemeProvider(ThemeProvider themeProvider) {
        this.themeProvider = themeProvider;
    }

    @Override
    public Theme getActiveTheme() {
        String style = Session.get().getStyle();

        if (Strings.isNullOrEmpty(style)) {
            return themeProvider.defaultTheme();
        } else {
            return themeProvider.byName(style);
        }
    }

    @Override
    public void setActiveTheme(String themeName) {
        setActiveTheme(themeProvider.byName(themeName));
    }

    @Override
    public void setActiveTheme(Theme theme) {
        assertBoundSession();

        if (theme != null) {
            Session.get().setStyle(theme.name());
        } else {
            Session.get().setStyle(null);
        }
    }

    private void assertBoundSession() {
        Session session = Session.get();

        if (Strings.isNullOrEmpty(session.getId())) {
            session.bind();
        }
    }
}
