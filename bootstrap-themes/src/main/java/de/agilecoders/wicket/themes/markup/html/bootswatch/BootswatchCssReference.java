package de.agilecoders.wicket.themes.markup.html.bootswatch;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A {@link org.apache.wicket.request.resource.CssResourceReference} for all bootswatch
 * themes. All themes can be collected by executing gettheme.sh.
 *
 * @author miha
 */
public class BootswatchCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    private final String swatchName;

    /**
     * Singleton instances of all bootswatch theme references
     */
    public static final BootswatchCssReference AMELIA = new BootswatchCssReference("amelia");
    public static final BootswatchCssReference CERULEAN = new BootswatchCssReference("cerulean");
    public static final BootswatchCssReference COSMO = new BootswatchCssReference("cosmo");
    public static final BootswatchCssReference CYBORG = new BootswatchCssReference("cyborg");
    public static final BootswatchCssReference FLATLY = new BootswatchCssReference("flatly");
    public static final BootswatchCssReference JOURNAL = new BootswatchCssReference("journal");
    public static final BootswatchCssReference READABLE = new BootswatchCssReference("readable");
    public static final BootswatchCssReference SIMPLEX = new BootswatchCssReference("simplex");
    public static final BootswatchCssReference SLATE = new BootswatchCssReference("slate");
    public static final BootswatchCssReference SPACELAB = new BootswatchCssReference("spacelab");
    public static final BootswatchCssReference UNITED = new BootswatchCssReference("united");
    public static final BootswatchCssReference YETI = new BootswatchCssReference("yeti");

    /**
     * Private constructor.
     */
    public BootswatchCssReference(final String swatchName) {
        super(BootswatchCssReference.class, "css/bootstrap." + swatchName + ".css");
        this.swatchName = swatchName;
    }

    /**
     * @return bootswatch theme name of this reference
     */
    public String getSwatchName() {
        return swatchName;
    }

}