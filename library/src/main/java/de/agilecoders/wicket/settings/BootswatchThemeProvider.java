package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.references.BootswatchCssReference;
import de.agilecoders.wicket.markup.html.themes.cerulean.CeruleanTheme;

/**
 * The {@code BootswatchThemeProvider} provides access to all available bootswatch themes.
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

        add(new CeruleanTheme(),
            new Theme("amelia", BootswatchCssReference.AMELIA),
            new Theme("cyborg", BootswatchCssReference.CYBORG),
            new Theme("journal", BootswatchCssReference.JOURNAL),
            new Theme("readable", BootswatchCssReference.READABLE),
            new Theme("spruce", BootswatchCssReference.SPRUCE),
            new Theme("spacelab", BootswatchCssReference.SPACELAB),
            new Theme("united", BootswatchCssReference.UNITED),
            new Theme("slate", BootswatchCssReference.SLATE),
            new Theme("simplex", BootswatchCssReference.SIMPLEX),
            new Theme("superhero", BootswatchCssReference.SUPERHERO));
    }


}
