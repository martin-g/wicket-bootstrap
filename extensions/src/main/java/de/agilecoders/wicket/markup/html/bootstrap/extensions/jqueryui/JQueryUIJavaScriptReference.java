package de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui;

import com.google.common.collect.Lists;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.List;

/**
 * core jquery ui package (core, widget, mouse, position, draggable, resizeable)
 *
 * @author miha
 */
public class JQueryUIJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final JQueryUIJavaScriptReference INSTANCE = new JQueryUIJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUIJavaScriptReference instance() {
        return INSTANCE;
    }


    /**
     * Private constructor.
     */
    private JQueryUIJavaScriptReference() {
        super(JQueryUIJavaScriptReference.class, "js/jquery-ui-1.9.2.core.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));

        return dependencies;
    }
}