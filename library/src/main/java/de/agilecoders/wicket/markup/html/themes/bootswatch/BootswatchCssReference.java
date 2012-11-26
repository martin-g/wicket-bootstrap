package de.agilecoders.wicket.markup.html.themes.bootswatch;

import de.agilecoders.wicket.less.Resource;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;

import java.util.List;

/**
 * A {@link org.apache.wicket.request.resource.CssResourceReference} for all bootswatch
 * themes. All themes can be collected by executing gettheme.sh.
 *
 * @author miha
 * @version 1.0
 */
public class BootswatchCssReference extends BootstrapCssReference {
    private static final long serialVersionUID = 1L;

    private final String swatchName;

    /**
     * Singleton instance of this reference
     */
    public static final BootswatchCssReference AMELIA = new BootswatchCssReference("amelia");
    public static final BootswatchCssReference CERULEAN = new BootswatchCssReference("cerulean");
    public static final BootswatchCssReference CYBORG = new BootswatchCssReference("cyborg");
    public static final BootswatchCssReference JOURNAL = new BootswatchCssReference("journal");
    public static final BootswatchCssReference READABLE = new BootswatchCssReference("readable");
    public static final BootswatchCssReference SIMPLEX = new BootswatchCssReference("simplex");
    public static final BootswatchCssReference SLATE = new BootswatchCssReference("slate");
    public static final BootswatchCssReference SPACELAB = new BootswatchCssReference("spacelab");
    public static final BootswatchCssReference SPRUCE = new BootswatchCssReference("spruce");
    public static final BootswatchCssReference SUPERHERO = new BootswatchCssReference("superhero");
    public static final BootswatchCssReference UNITED = new BootswatchCssReference("united");
    public static final BootswatchCssReference COSMO = new BootswatchCssReference("cosmo");

    /**
     * Private constructor.
     */
    public BootswatchCssReference(final String swatchName) {
        super(BootswatchCssReference.class, "css/bootstrap." + swatchName + ".css");
        this.swatchName = swatchName;
    }

    public String getSwatchName() {
        return swatchName;
    }

    @Override
    public List<Resource> getLessResources() {
        final List<Resource> resources = super.getLessResources();
        resources.add(resourceLocator().findResource(BootswatchCssReference.class, "less/" + swatchName + "/variables.less"));
        resources.add(resourceLocator().findResource(BootswatchCssReference.class, "less/" + swatchName + "/bootswatch.less"));
        resources.add(resourceLocator().findResource(BootstrapCssReference.class, "less/utilities.less"));
        return resources;
    }
}