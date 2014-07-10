package de.agilecoders.wicket.themes.markup.html.vegibit;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A {@link org.apache.wicket.request.resource.CssResourceReference} for all vegibit
 * themes. All themes can be collected by executing gettheme.sh.
 *
 * @author miha
 */
public class VegibitCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public VegibitCssReference(final String name) {
        super(VegibitCssReference.class, "css/bootstrap." + name + ".css");
    }

}