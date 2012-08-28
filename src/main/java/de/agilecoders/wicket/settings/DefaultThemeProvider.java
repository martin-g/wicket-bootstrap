package de.agilecoders.wicket.settings;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;
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

    private final List<Theme> themes = Lists.newArrayList();
    private Theme defaultTheme;

    /**
     * Construct.
     */
    public DefaultThemeProvider() {
        addDefaultTheme(new Theme("bootstrap", BootstrapCssReference.INSTANCE));
    }

    public DefaultThemeProvider add(Theme... themes) {
        assertNoDuplicateNames(themes);

        this.themes.addAll(Lists.newArrayList(themes));
        return this;
    }

    private void assertNoDuplicateNames(Theme... themes) {
        if (themes == null) {
            throw new WicketRuntimeException("list of themes is null");
        }

        for (Theme newTheme : themes) {
            if (newTheme == null) {
                throw new WicketRuntimeException("theme is null");
            }

            for (Theme existingTheme : this.themes) {
                if (existingTheme.equals(newTheme)) {
                    throw new WicketRuntimeException("duplicated theme entry: " + newTheme.name());
                }

                if (newTheme.name().equalsIgnoreCase(existingTheme.name())) {
                    throw new WicketRuntimeException("duplicated theme name: " + newTheme.name());
                }
            }
        }
    }

    public DefaultThemeProvider addDefaultTheme(Theme theme) {
        add(theme);
        return defaultTheme(theme);
    }

    public DefaultThemeProvider defaultTheme(Theme theme) {
        return defaultTheme(theme.name());
    }

    public DefaultThemeProvider defaultTheme(String themeName) {
        Theme newDefaultTheme = byName(themeName);

        if (defaultTheme != newDefaultTheme) {
            defaultTheme = newDefaultTheme;
        }

        return this;
    }

    @Override
    public Theme byName(String name) {
        if (!Strings.isNullOrEmpty(name)) {
            for (Theme theme : themes) {
                if (name.equalsIgnoreCase(theme.name())) {
                    return theme;
                }
            }
        }

        throw new WicketRuntimeException("theme does not exists: " + name);
    }

    @Override
    public List<Theme> available() {
        return ImmutableList.copyOf(themes);
    }

    @Override
    public Theme defaultTheme() {
        return defaultTheme;
    }
}
