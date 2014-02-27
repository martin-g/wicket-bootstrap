package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.List;

/**
 * JavaScript resource reference for jqueryui.resizable.js
 */
public class JQueryUIResizableJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryUIResizableJavaScriptReference INSTANCE = new JQueryUIResizableJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUIResizableJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private JQueryUIResizableJavaScriptReference() {
        super("jquery-ui/current/ui/minified/jquery.ui.resizable.min.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JQueryUIWidgetJavaScriptReference.asHeaderItem());
        dependencies.add(JQueryUIMouseJavaScriptReference.asHeaderItem());

        return dependencies;
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
