package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import org.apache.wicket.request.resource.CssResourceReference;


public class RatingCssResourceReference extends CssResourceReference {

	private static final long serialVersionUID = 1L;

	private static final RatingCssResourceReference INSTANCE = new RatingCssResourceReference();

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
    public static RatingCssResourceReference getInstance() {
        return INSTANCE;
    }
}
