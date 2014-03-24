package de.agilecoders.wicket.themes.settings;

import de.agilecoders.wicket.core.settings.DefaultThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;

/**
 * The {@code BootswatchThemeProvider} provides access to all available
 * bootswatch themes.
 * 
 * @author miha
 */
public class BootswatchThemeProvider extends DefaultThemeProvider {

    /**
     * Construct.
     *
     * @param settings bootstrap settings to use.
     */
    public BootswatchThemeProvider(IBootstrapSettings settings) {
        super(settings);

        addBootswatchThemes();
    }

    /**
     * Constructor.
     */
    @Deprecated
    public BootswatchThemeProvider() {
        super();

        addBootswatchThemes();
    }

    /**
     * add all available bootswatch themes.
     */
    private void addBootswatchThemes() {
        add(BootswatchTheme.AMELIA, BootswatchTheme.CERULEAN,
            BootswatchTheme.COSMO, BootswatchTheme.CYBORG,
            BootswatchTheme.FLATLY,BootswatchTheme.JOURNAL,
            BootswatchTheme.READABLE, BootswatchTheme.SIMPLEX,
            BootswatchTheme.SLATE, BootswatchTheme.SPACELAB,
            BootswatchTheme.SUPERHERO, BootswatchTheme.LUMEN,
            BootswatchTheme.UNITED, BootswatchTheme.YETI);
    }
}
