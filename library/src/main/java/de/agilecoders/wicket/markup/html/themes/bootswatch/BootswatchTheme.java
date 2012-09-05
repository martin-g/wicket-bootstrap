package de.agilecoders.wicket.markup.html.themes.bootswatch;

import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.settings.Theme;

/**
 * Bootstrap theme.
 *
 * @author miha
 * @version 1.0
 */
public class BootswatchTheme extends Theme {

    public static final BootswatchTheme AMELIA		= new BootswatchTheme(BootswatchCssReference.AMELIA);
    public static final BootswatchTheme CERULEAN	= new BootswatchTheme(BootswatchCssReference.CERULEAN);
    public static final BootswatchTheme CYBORG		= new BootswatchTheme(BootswatchCssReference.CYBORG);
    public static final BootswatchTheme JOURNAL		= new BootswatchTheme(BootswatchCssReference.JOURNAL);
    public static final BootswatchTheme READABLE	= new BootswatchTheme(BootswatchCssReference.READABLE);
    public static final BootswatchTheme SIMPLEX 	= new BootswatchTheme(BootswatchCssReference.SIMPLEX);
    public static final BootswatchTheme SLATE 		= new BootswatchTheme(BootswatchCssReference.SLATE);
    public static final BootswatchTheme SPACELAB 	= new BootswatchTheme(BootswatchCssReference.SPACELAB);
    public static final BootswatchTheme SPRUCE 		= new BootswatchTheme(BootswatchCssReference.SPRUCE);
    public static final BootswatchTheme SUPERHERO 	= new BootswatchTheme(BootswatchCssReference.SUPERHERO);
    public static final BootswatchTheme UNITED		= new BootswatchTheme(BootswatchCssReference.UNITED);

	/**
     * Construct.
     */
    public BootswatchTheme(final String name, final ResourceReference... resourceReferences) {
        super(name, resourceReferences);
    }

    /**
     * Construct.
     */
    public BootswatchTheme(final BootswatchCssReference reference) {
	this(reference.getSwatchName(), reference);
    }


}
