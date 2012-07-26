package de.agilecoders.wicket.settings;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.references.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.references.BootswatchCssReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootswatchThemeProvider implements ThemeProvider {

    List<Theme> themes = Lists.newArrayList();
    private Theme defaultTheme;

    public BootswatchThemeProvider() {
        defaultTheme = new Theme("bootstrap", BootstrapCssReference.INSTANCE);

        themes.add(defaultTheme);
        themes.add(new Theme("cerulean", BootswatchCssReference.CERULEAN));
        themes.add(new Theme("amelia", BootswatchCssReference.AMELIA));
        themes.add(new Theme("cyborg", BootswatchCssReference.CYBORG));
        themes.add(new Theme("journal", BootswatchCssReference.JOURNAL));
        themes.add(new Theme("readable", BootswatchCssReference.READABLE));
        themes.add(new Theme("spruce", BootswatchCssReference.SPRUCE));
        themes.add(new Theme("spacelab", BootswatchCssReference.SPACELAB));
        themes.add(new Theme("united", BootswatchCssReference.UNITED));
        themes.add(new Theme("slate", BootswatchCssReference.SLATE));
        themes.add(new Theme("simplex", BootswatchCssReference.SIMPLEX));
        themes.add(new Theme("superhero", BootswatchCssReference.SUPERHERO));
        //themes.add(new Theme("metro", BootswatchCssReference.METRO));
    }

    protected void add(Theme... theme) {
        themes.addAll(Lists.newArrayList(theme));
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

        return defaultTheme;
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
