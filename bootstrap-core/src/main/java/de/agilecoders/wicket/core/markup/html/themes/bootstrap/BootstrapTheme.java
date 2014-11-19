package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * #### Description
 *
 * Bootstrap theme that uses the css resource reference from bootstrap settings.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class BootstrapTheme extends Theme {

    /**
     * Construct.
     */
    public BootstrapTheme() {
        super("bootstrap");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> references = new ArrayList<>();
        ResourceReference cssResourceReference;
        if (Application.exists()) {
            IBootstrapSettings settings = Bootstrap.getSettings();
            cssResourceReference = settings.getCssResourceReference();
        } else {
            cssResourceReference = BootstrapCssReference.instance();
        }
        references.add(CssHeaderItem.forReference(cssResourceReference).setId(BOOTSTRAP_THEME_MARKUP_ID));
        return references;
    }
}
