package de.agilecoders.wicket.themes.markup.html.wicket;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;
import java.util.List;

/**
 * Wicket theme css resource reference
 *
 * @author miha
 */
public class WicketThemeCssResourceReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final WicketThemeCssResourceReference INSTANCE = new WicketThemeCssResourceReference();

    /**
     * Private constructor.
     */
    public WicketThemeCssResourceReference() {
        super(WicketThemeCssResourceReference.class, "css/bootstrap.wicket.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        ResourceReference cssResourceReference;
        if (Application.exists()) {
            cssResourceReference = Bootstrap.getSettings().getCssResourceReference();
        } else {
            cssResourceReference = BootstrapCssReference.instance();
        }
        return Arrays.<HeaderItem>asList(CssHeaderItem.forReference(cssResourceReference));
    }
}
