package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.themes.bootswatch.BootswatchTheme;

/**
 * The {@code BootswatchThemeProvider} provides access to all available
 * bootswatch themes.
 *
 * @author miha
 * @version 1.0
 */
public class BootswatchThemeProvider extends DefaultThemeProvider {

    /**
     * Constructor.
     */
    public BootswatchThemeProvider() {
        super();

        add(BootswatchTheme.AMELIA, BootswatchTheme.CERULEAN,
            BootswatchTheme.CYBORG, BootswatchTheme.JOURNAL,
            BootswatchTheme.READABLE, BootswatchTheme.SIMPLEX,
            BootswatchTheme.SLATE, BootswatchTheme.SPACELAB,
            BootswatchTheme.SPRUCE, BootswatchTheme.SUPERHERO,
            BootswatchTheme.UNITED, BootswatchTheme.COSMO);
    }

}
