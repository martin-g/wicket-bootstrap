package de.agilecoders.wicket.themes.markup.html.bootswatch;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;

/**
 * Bootstrap theme.
 *
 * @author miha
 */
public class BootswatchTheme extends Theme {

    /**
     * The placeholders are:
     * - the version
     * - the theme name
     * Example: //netdna.bootstrapcdn.com/bootswatch/2.3.0/amelia/bootstrap.min.css
     */
    private static final String CDN_PATTERN = "//netdna.bootstrapcdn.com/bootswatch/%s/%s/bootstrap.min.css";

    public static final BootswatchTheme AMELIA = new BootswatchTheme(BootswatchCssReference.AMELIA);
    public static final BootswatchTheme CERULEAN = new BootswatchTheme(BootswatchCssReference.CERULEAN);
    public static final BootswatchTheme CYBORG = new BootswatchTheme(BootswatchCssReference.CYBORG);
    public static final BootswatchTheme JOURNAL = new BootswatchTheme(BootswatchCssReference.JOURNAL);
    public static final BootswatchTheme READABLE = new BootswatchTheme(BootswatchCssReference.READABLE);
    public static final BootswatchTheme SIMPLEX = new BootswatchTheme(BootswatchCssReference.SIMPLEX);
    public static final BootswatchTheme SLATE = new BootswatchTheme(BootswatchCssReference.SLATE);
    public static final BootswatchTheme SPACELAB = new BootswatchTheme(BootswatchCssReference.SPACELAB);
    public static final BootswatchTheme SPRUCE = new BootswatchTheme(BootswatchCssReference.SPRUCE);
    public static final BootswatchTheme SUPERHERO = new BootswatchTheme(BootswatchCssReference.SUPERHERO);
    public static final BootswatchTheme UNITED = new BootswatchTheme(BootswatchCssReference.UNITED);
    public static final BootswatchTheme COSMO = new BootswatchTheme(BootswatchCssReference.COSMO);

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
        this(reference.getSwatchName(), reference, BootstrapResponsiveCssReference.INSTANCE);
    }

    @Override
    public Iterable<String> getCdnUrls() {
        String cdnUrl = String.format(CDN_PATTERN, getVersion(), name());
        return Arrays.asList(cdnUrl);
    }
}
