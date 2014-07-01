package de.agilecoders.wicket.themes.markup.html.google;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

/**
 * #### Description
 *
 * css resource reference that references `google-bootstrap.css`.
 *
 * #### Usage
 *
 * ```java
 * response.render(CssHeaderItem.forReference(GoogleCssReference.instance()));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
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
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
    }
}
