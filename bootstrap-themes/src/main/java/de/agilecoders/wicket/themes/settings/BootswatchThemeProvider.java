package de.agilecoders.wicket.themes.settings;

import com.google.common.collect.ImmutableList;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
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
        this.themes = ImmutableList.<ITheme>builder().add(BootswatchTheme.values()).build();
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
