package de.agilecoders.wicket.core.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class TypeaheadJsReference extends JavaScriptResourceReference {

    private static final TypeaheadJsReference INSTANCE = new TypeaheadJsReference();

    private TypeaheadJsReference() {
        super(TypeaheadJsReference.class, "js/typeahead.js");
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
