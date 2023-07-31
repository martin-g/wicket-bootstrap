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

    /**
     * Private constructor.
     */
    public BootswatchCssReference(final String swatchName) {
        super(BootswatchCssReference.class, "css/bootstrap." + swatchName + ".css");
    }
}
