package de.agilecoders.wicket.core.markup.html.references;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * represents the prettify js library
 *
 * @author miha
 */
public class BootstrapPrettifyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapPrettifyJavaScriptReference();

    /**
     * Private constructor.
     */
    private BootstrapPrettifyJavaScriptReference() {
        super(BootstrapPrettifyJavaScriptReference.class, "js/prettify.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                                    JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
    }
}
