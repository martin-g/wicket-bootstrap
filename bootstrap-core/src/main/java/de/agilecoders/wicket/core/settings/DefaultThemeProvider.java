package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapTheme;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.string.Strings;

import java.util.Collections;
import java.util.List;

/**
 * #### Description
 *
 * A default {@link ThemeProvider} implementation that only contains
 * the {@link de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapTheme}.
 *
 * #### Usage
 *
 * ```java
 * settings.setThemeProvider(new DefaultThemeProvider(settings));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class DefaultThemeProvider implements ThemeProvider {

    private final List<ITheme> themes = Generics2.newArrayList();
    private ITheme defaultTheme;

    /**
     * Construct.
     */
    public DefaultThemeProvider() {
        addDefaultTheme(new BootstrapTheme());
    }

    /**
     * adds an array of new themes.
     *
     * @param themes The new themes
     * @return This instance
     */
    public DefaultThemeProvider add(final ITheme... themes) {
        assertNoDuplicateNames(themes);

        this.themes.addAll(Generics2.newArrayList(themes));
        return this;
    }

    /**
     * asserts that there are no name conflicts.
     *
     * @param themes The themes to check.
     */
    private void assertNoDuplicateNames(final ITheme... themes) {
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
    public DefaultThemeProvider addDefaultTheme(final ITheme theme) {
        add(theme);
        return defaultTheme(theme);
    }

    /**
     * sets the default theme.
     *
     * @param newDefaultTheme The new default theme
     * @return This instance
     */
    public DefaultThemeProvider defaultTheme(final ITheme newDefaultTheme) {
        if (newDefaultTheme != null && !newDefaultTheme.equals(defaultTheme)) {
            defaultTheme = newDefaultTheme;
        }

        return this;
    }

    /**
     * sets the default theme.
     *
     * @param themeName The name of the new default theme
     * @return This instance
     */
    public DefaultThemeProvider defaultTheme(final String themeName) {
        ITheme newDefaultTheme = byName(themeName);
        return defaultTheme(newDefaultTheme);
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
        return Collections.unmodifiableList(themes);
    }

    @Override
    public ITheme defaultTheme() {
        return defaultTheme;
    }
}
