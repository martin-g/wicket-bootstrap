package de.agilecoders.wicket.themes.markup.html.vegibit;

import com.google.common.collect.ImmutableList;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.List;

/**
 * The {@code VegibitThemeProvider} provides access to all available
 * vegibit themes.
 * 
 * @author miha
 */
public class VegibitThemeProvider implements ThemeProvider {

    private final List<ITheme> themes;
    private final ITheme defaultTheme;

    /**
     * Constructor.
     */
    public VegibitThemeProvider(final String defaultThemeName) {
        this(VegibitTheme.valueOf(defaultThemeName));
    }

    /**
     * Constructor.
     */
    public VegibitThemeProvider(final VegibitTheme defaultTheme) {
        this.themes = ImmutableList.<ITheme>builder().add(VegibitTheme.values()).build();
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
