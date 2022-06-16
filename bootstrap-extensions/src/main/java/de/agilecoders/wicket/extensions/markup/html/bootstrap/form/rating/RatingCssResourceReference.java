package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import java.util.List;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import com.google.common.collect.Lists;


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

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(CssHeaderItem.forReference(FontAwesomeSettings.get(Application.get()).getCssResourceReference()));

        return dependencies;
    }
}
