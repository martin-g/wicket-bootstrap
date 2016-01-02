package de.agilecoders.wicket.extensions.slider.res;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * BootsTrapSliderResourceReference
 */
public class BootstrapSliderResourceReference extends JavaScriptResourceReference {
    
    private static final BootstrapSliderResourceReference instance = new BootstrapSliderResourceReference();

    public BootstrapSliderResourceReference() {
        super(BootstrapSliderResourceReference.class, "bootstrap-slider.js");
    }

    public static BootstrapSliderResourceReference getInstance() {
        return instance;
    }
}
