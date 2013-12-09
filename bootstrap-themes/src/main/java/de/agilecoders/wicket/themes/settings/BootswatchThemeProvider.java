package de.agilecoders.wicket.themes.settings;

import de.agilecoders.wicket.core.settings.DefaultThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;

/**
 * The {@code BootswatchThemeProvider} provides access to all available
 * bootswatch themes.
 * 
 * @author miha
 */
public class BootswatchThemeProvider extends DefaultThemeProvider {

    /**
     * Constructor.
     */
    public BootswatchThemeProvider() {
        super();

        add(BootswatchTheme.AMELIA, BootswatchTheme.CERULEAN,
            BootswatchTheme.COSMO, BootswatchTheme.CYBORG, 
            BootswatchTheme.FLATLY,BootswatchTheme.JOURNAL,
            BootswatchTheme.READABLE, BootswatchTheme.SIMPLEX,
            BootswatchTheme.SLATE, BootswatchTheme.SPACELAB,
            BootswatchTheme.UNITED, BootswatchTheme.YETI);
    }
}
