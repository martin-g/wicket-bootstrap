package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 *
 */
public class BootstrapTourCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;
    public static final BootstrapTourCssReference INSTANCE = new BootstrapTourCssReference();

    private BootstrapTourCssReference() {
        super(BootstrapTourCssReference.class, "css/bootstrap-tour.css");
    }
}
