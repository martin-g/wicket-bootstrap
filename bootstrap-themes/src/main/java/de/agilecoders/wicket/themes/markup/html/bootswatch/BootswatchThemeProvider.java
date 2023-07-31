package de.agilecoders.wicket.themes.markup.html.bootswatch;

import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.List;

/**
 * The {@code BootswatchThemeProvider} provides access to all available
 * bootswatch themes.
 *
 * @author miha
 */
public class BootswatchThemeProvider implements ThemeProvider {

    private final List<ITheme> themes;
    private final ITheme defaultTheme;

    /**
     * Constructor.
     */
    public BootswatchThemeProvider(final String defaultThemeName) {
        this(BootswatchTheme.valueOf(defaultThemeName));
    }

    /**
     * Constructor.
     */
    public BootswatchThemeProvider(final BootswatchTheme defaultTheme) {
        this.themes = List.of(BootswatchTheme.values());
        this.defaultTheme = Args.notNull(defaultTheme, "defaultTheme");
    }

    @Override
    public ITheme byName(String name) {
        if (!Strings.isEmpty(name)) {
            for (ITheme theme : themes) {
                if (name.equalsIgnoreCase(theme.name())) {
                    return theme;
                }
            }
        }

        throw new WicketRuntimeException("theme does not exists: " + name);
    }

    @Override
    public List<ITheme> available() {
        return themes;
    }

    @Override
    public ITheme defaultTheme() {
        return defaultTheme;
    }
}
