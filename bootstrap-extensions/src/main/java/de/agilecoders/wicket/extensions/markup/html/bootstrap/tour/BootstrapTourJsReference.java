package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.JQueryCookieJsReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.Locale;

/**
 * A JavaScript resource reference that contributes the bootstrap-tour.js and
 * its dependencies.
 */
public class BootstrapTourJsReference extends JavaScriptResourceReference {
    public static final BootstrapTourJsReference INSTANCE = new BootstrapTourJsReference();

    public BootstrapTourJsReference() {
        this(null, null, null);
    }

    public BootstrapTourJsReference(Locale locale, String style, String variation) {
        super(BootstrapTourJsReference.class, "js/bootstrap-tour.js", locale, style, variation);
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                                    CssHeaderItem.forReference(BootstrapTourCssReference.INSTANCE),
                                    JavaScriptHeaderItem.forReference(JQueryCookieJsReference.INSTANCE),
                                    JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
    }
}
