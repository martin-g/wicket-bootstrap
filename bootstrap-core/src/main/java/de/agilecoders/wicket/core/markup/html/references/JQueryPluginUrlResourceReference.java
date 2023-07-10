package de.agilecoders.wicket.core.markup.html.references;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;

/**
 * A specialization of UrlResourceReference that depends on JQuery
 *
 * @see org.apache.wicket.settings.JavaScriptLibrarySettings#getJQueryReference()
 */
public class JQueryPluginUrlResourceReference extends UrlResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param url the url of the external resource
     */
    public JQueryPluginUrlResourceReference(Url url) {
        super(url);
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final ResourceReference backingLibraryReference;
        if (Application.exists())
        {
            backingLibraryReference = Application.get()
                    .getJavaScriptLibrarySettings()
                    .getJQueryReference();
        }
        else
        {
            backingLibraryReference = JQueryResourceReference.getV3();
        }
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem.forReference(backingLibraryReference));
    }
}
