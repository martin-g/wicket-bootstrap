package de.agilecoders.wicket.core.markup.html.references;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;

/**
 * A specialization of UrlResourceReference that depends on JQuery and Popper.js.
 *
 * @author Jan Ferko
 */
public class PopperPluginUrlResourceReference extends JQueryPluginUrlResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param url the url of the external resource
     */
    public PopperPluginUrlResourceReference(Url url) {
        super(url);
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = super.getDependencies();

        ResourceReference backingLibraryReference;
        if (Application.exists()) {
            backingLibraryReference = Bootstrap.getSettings().getPopperJsResourceReference();
        } else {
            backingLibraryReference = PopperJavaScriptReference.instance();
        }

        return Dependencies.combine(dependencies, JavaScriptHeaderItem.forReference(backingLibraryReference));
    }
}
