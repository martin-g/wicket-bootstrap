package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapTheme;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link SingleThemeProvider}
 *
 * @author miha
 */
class SingleThemeProviderTest {

    @Test
    void availableThemesReturnsSingleTheme() {
        ITheme theme = new BootstrapTheme();
        ThemeProvider provider = new SingleThemeProvider(theme);

        assertThat(provider.available().size(), is(1));
        assertThat(provider.available(), hasItem(theme));
    }

    @Test
    void defaultThemeReturnsSingleTheme() {
        ITheme theme = new BootstrapTheme();
        ThemeProvider provider = new SingleThemeProvider(theme);

        assertThat(provider.defaultTheme(), is(theme));
    }

    @Test
    void byNameReturnsSingleTheme() {
        ITheme theme = new BootstrapTheme();
        ThemeProvider provider = new SingleThemeProvider(theme);

        assertThat(provider.byName("a"), is(theme));
        assertThat(provider.byName("b"), is(theme));
        assertThat(provider.byName("c"), is(theme));
        assertThat(provider.byName("bootstrap"), is(theme));
    }
}
