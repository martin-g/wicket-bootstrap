package de.agilecoders.wicket.themes.markup.html.metro;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Metro theme.
 *
 * @author miha
 * @version 1.0
 */
public class MetroTheme extends Theme {

    /**
     * Construct.
     */
    public MetroTheme(final String name) {
        super(name);
    }

    /**
     * Construct.
     */
    public MetroTheme() {
        this("metro");
    }

    @Override
    public List<ResourceReference> getResourceReferences() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        references.addAll(super.getResourceReferences());
        references.add(BootstrapCssReference.instance());
        references.add(MetroCssReference.instance());
        return references;
    }
}
