package de.agilecoders.wicket.themes.markup.html.bootstrap;

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
public class Bootstrap3ThemeCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * @return singleton instance of {@link Bootstrap3ThemeCssReference}
     */
    public static Bootstrap3ThemeCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final Bootstrap3ThemeCssReference INSTANCE = new Bootstrap3ThemeCssReference();
    }

    /**
     * Private constructor.
     */
    private Bootstrap3ThemeCssReference() {
        super(Bootstrap3ThemeCssReference.class, "css/bootstrap-theme.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Generics2.newArrayList(super.getDependencies());
        dependencies.add(CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));

        return dependencies;
    }
}
