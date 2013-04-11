package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 *
 */
public class BootstrapTourCssReference extends CssResourceReference {

    public static final BootstrapTourCssReference INSTANCE = new BootstrapTourCssReference();

    private BootstrapTourCssReference() {
        super(BootstrapTourCssReference.class, "css/bootstrap-tour.css");
    }


}
