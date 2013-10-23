package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TypeaheadJsReference extends WebjarsJavaScriptResourceReference {

    private static final TypeaheadJsReference INSTANCE = new TypeaheadJsReference();

    private TypeaheadJsReference() {
        super("/typeaheadjs/current/typeahead.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
        dependencies.add(JavaScriptHeaderItem.forReference(BootstrapJavaScriptReference.instance()));
        return dependencies;
    }

    public static TypeaheadJsReference instance() {
        return INSTANCE;
    }
}
