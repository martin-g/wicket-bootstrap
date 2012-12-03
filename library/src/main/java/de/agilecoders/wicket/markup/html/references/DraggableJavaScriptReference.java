package de.agilecoders.wicket.markup.html.references;

import com.google.common.collect.Lists;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.List;

/**
 * A {@link JavaScriptResourceReference} that includes the jquery draggable plugin
 *
 * @author miha
 */
public class DraggableJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final DraggableJavaScriptReference INSTANCE = new DraggableJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static DraggableJavaScriptReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private DraggableJavaScriptReference() {
        super(DraggableJavaScriptReference.class, "js/jquery.event.drag-2.2.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));

        return dependencies;
    }
}