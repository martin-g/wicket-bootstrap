package de.agilecoders.wicket.markup.html.references;

import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final BootstrapJavaScriptReference INSTANCE = new BootstrapJavaScriptReference();


    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.settings.IBootstrapSettings#getJsResourceReference()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static BootstrapJavaScriptReference get() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private BootstrapJavaScriptReference() {
        super(BootstrapJavaScriptReference.class, "js/bootstrap.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Generics2.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));

        return dependencies;
    }
}