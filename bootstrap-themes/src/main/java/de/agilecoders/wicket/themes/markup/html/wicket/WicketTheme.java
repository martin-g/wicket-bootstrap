package de.agilecoders.wicket.themes.markup.html.wicket;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Wicket theme.
 *
 * @author miha
 */
public class WicketTheme extends Theme {

    /**
     * Construct.
     */
    public WicketTheme() {
        super("wicket");
    }

    @Override
    public List<ResourceReference> getResourceReferences() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        references.addAll(super.getResourceReferences());
        references.add(BootstrapCssReference.instance());
        references.add(WicketThemeCssResourceReference.INSTANCE);
        return references;
    }
}
