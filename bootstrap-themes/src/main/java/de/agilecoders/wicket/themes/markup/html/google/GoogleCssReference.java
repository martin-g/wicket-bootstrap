package de.agilecoders.wicket.themes.markup.html.google;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import java.util.List;

/**
 * A google inspired theme
 *
 * @author miha
 */
public class GoogleCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * @return singleton instance of {@link GoogleCssReference}
     */
    public static GoogleCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final GoogleCssReference INSTANCE = new GoogleCssReference();
    }

    /**
     * Private constructor.
     */
    private GoogleCssReference() {
        super(GoogleCssReference.class, "css/google-bootstrap.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Generics2.newArrayList(super.getDependencies());
        dependencies.add(CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));

        return dependencies;
    }
}
