package de.agilecoders.wicket.extensions.markup.html.references;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * represents a reference to the jquerypp javascript resource
 *
 * @author miha
 */
public class JqueryPPJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new JqueryPPJavaScriptReference();

    /**
     * Private constructor.
     */
    private JqueryPPJavaScriptReference() {
        super("/jquerypp/current/jquerypp.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                                    JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }
}