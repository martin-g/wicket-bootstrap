package de.agilecoders.wicket.extensions.slider.res;

import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * BootsTrapSliderResourceReference
 */
public class BootstrapSliderCssResourceReference extends PackageResourceReference {

    private static final BootstrapSliderCssResourceReference instance = new BootstrapSliderCssResourceReference();

    public BootstrapSliderCssResourceReference() {
        super(BootstrapSliderCssResourceReference.class, "css/bootstrap-slider.min.css");
    }

    public static BootstrapSliderCssResourceReference getInstance() {
        return instance;
    }
}
