package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.List;

/**
 * core jquery ui package (core, widget, mouse, position, draggable, resizeable)
 *
 * @author miha
 */
public class JQueryUICoreJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryUICoreJavaScriptReference INSTANCE = new JQueryUICoreJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUICoreJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private JQueryUICoreJavaScriptReference() {
        super("jquery-ui/current/ui/minified/jquery.ui.core.min.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));

        return dependencies;
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
