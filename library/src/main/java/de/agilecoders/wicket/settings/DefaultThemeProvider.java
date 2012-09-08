package de.agilecoders.wicket.settings;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapTheme;
import de.agilecoders.wicket.markup.html.themes.wicket.WicketTheme;
import org.apache.wicket.WicketRuntimeException;

import java.util.List;

/**
 * A default {@link ThemeProvider} implementation that only contains
 * the {@link de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapTheme}.
 *
 * @author miha
 * @version 1.0
 */
public class DefaultThemeProvider implements ThemeProvider {

    private final List<ITheme> themes = Lists.newArrayList();
    private ITheme defaultTheme;

    /**
     * Construct.
     */
    public DefaultThemeProvider() {
        addDefaultTheme(new BootstrapTheme());
        add(new WicketTheme());
    }

    /**
     * adds an array of new themes.
     *
     * @param themes The new themes
     * @return This instance
     */
    public DefaultThemeProvider add(ITheme... themes) {
        assertNoDuplicateNames(themes);

        this.themes.addAll(Lists.newArrayList(themes));
        return this;
    }

    private void assertNoDuplicateNames(ITheme... themes) {
        if (themes == null) {
            throw new WicketRuntimeException("list of themes is null");
        }

        for (ITheme newTheme : themes) {
            if (newTheme == null) {
                throw new WicketRuntimeException("theme is null");
            }

            for (ITheme existingTheme : this.themes) {
                if (existingTheme.equals(newTheme)) {
                    throw new WicketRuntimeException("duplicated theme entry: " + newTheme.name());
                }

                if (newTheme.name().equalsIgnoreCase(existingTheme.name())) {
                    throw new WicketRuntimeException("duplicated theme name: " + newTheme.name());
                }
            }
        }
    }

    /**
     * adds a new theme and sets it as default theme.
     *
     * @param theme The new default theme
     * @return This instance
     */
    public DefaultThemeProvider addDefaultTheme(ITheme theme) {
        add(theme);
        return defaultTheme(theme);
    }

    /**
     * sets the default theme.
     *
     * @param theme The new default theme
     * @return This instance
     */
    public DefaultThemeProvider defaultTheme(ITheme theme) {
        return defaultTheme(theme.name());
    }

    /**
     * sets the default theme.
     *
     * @param themeName The new default theme
     * @return This instance
     */
    public DefaultThemeProvider defaultTheme(String themeName) {
        ITheme newDefaultTheme = byName(themeName);

        if (defaultTheme != newDefaultTheme) {
            defaultTheme = newDefaultTheme;
        }

        return this;
    }

    @Override
    public ITheme byName(String name) {
        if (!Strings.isNullOrEmpty(name)) {
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
        return ImmutableList.copyOf(themes);
    }

    @Override
    public ITheme defaultTheme() {
        return defaultTheme;
    }
}
