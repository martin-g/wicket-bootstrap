package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Language specific datepicker resource reference.
 *
 * @author miha
 */
public class BootstrapDatepickerLangJsReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Private constructor.
     */
    public BootstrapDatepickerLangJsReference(final String language) {
        super(BootstrapDatepickerLangJsReference.class, "js/lang/bootstrap-datepicker." + language + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = new ArrayList<>(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));

        return dependencies;
    }
}
