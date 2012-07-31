package de.agilecoders.wicket.settings;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.references.BootstrapCssReference;
import org.apache.wicket.WicketRuntimeException;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DefaultThemeProvider implements ThemeProvider {

    List<Theme> themes = Lists.newArrayList();
    private Theme defaultTheme;

    public DefaultThemeProvider() {
        defaultTheme = new Theme("bootstrap", BootstrapCssReference.INSTANCE);

        themes.add(defaultTheme);
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
                if (newTheme.name().equalsIgnoreCase(existingTheme.name())) {
                    throw new WicketRuntimeException("duplicated theme name: " + newTheme.name());
                }
            }
        }
    }

    public DefaultThemeProvider defaultTheme(Theme theme) {
        return defaultTheme(theme.name());
    }

    public DefaultThemeProvider defaultTheme(String themeName) {
        Theme newDefaultTheme = byName(themeName);

        if (defaultTheme != newDefaultTheme) {
            defaultTheme = newDefaultTheme;
        } else {
            // throw WicketRuntimeException(???);
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
