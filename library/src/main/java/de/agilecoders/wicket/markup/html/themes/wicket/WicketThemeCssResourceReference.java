package de.agilecoders.wicket.markup.html.themes.wicket;

import de.agilecoders.wicket.less.Resource;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class WicketThemeCssResourceReference extends BootstrapCssReference {
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
    public List<Resource> getLessResources() {
        List<Resource> resources = super.getLessResources();
        resources.add(resourceLocator().findResource(WicketThemeCssResourceReference.class, "less/wicket.less"));

        return resources;
    }
}