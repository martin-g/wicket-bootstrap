package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public class RatingJavaScriptResourceReference extends JQueryPluginResourceReference{

	private static final long serialVersionUID = 1L;

    private static final RatingJavaScriptResourceReference INSTANCE = new RatingJavaScriptResourceReference();


    /**
     * Gets the single instance of RatingJavaScriptResourceReference.
     *
     * @return single instance of RatingJavaScriptResourceReference
     */
    public static RatingJavaScriptResourceReference getInstance() {
        return INSTANCE;
    }

    /**
     * Instantiates a new rating java script resource reference.
     */
    private RatingJavaScriptResourceReference() {
        super(RatingJavaScriptResourceReference.class, "js/bootstrap-rating.js");
    }

}
