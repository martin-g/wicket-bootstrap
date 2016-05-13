package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import org.apache.wicket.request.resource.PackageResourceReference;

import de.agilecoders.wicket.extensions.slider.res.BootstrapSliderCssResourceReference;

public class RatingCssResourceReference extends PackageResourceReference {

	private static final long serialVersionUID = 1L;

	private static final BootstrapSliderCssResourceReference INSTANCE = new BootstrapSliderCssResourceReference();

    /**
     * Instantiates a new rating css resource reference.
     */
    public RatingCssResourceReference() {
        super(RatingCssResourceReference.class, "css/bootstrap-rating.css");
    }

    /**
     * Gets the single instance of RatingCssResourceReference.
     *
     * @return single instance of RatingCssResourceReference
     */
    public static BootstrapSliderCssResourceReference getInstance() {
        return INSTANCE;
    }
}
