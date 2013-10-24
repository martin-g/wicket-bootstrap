package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.List;

/**
 * JavaScript resource reference for jqueryui.widget.js
 */
public class JQueryUIWidgetJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final JQueryUIWidgetJavaScriptReference INSTANCE = new JQueryUIWidgetJavaScriptReference();

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUIWidgetJavaScriptReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private JQueryUIWidgetJavaScriptReference() {
        super("jquery-ui/current/ui/minified/jquery.ui.widget.min.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JQueryUICoreJavaScriptReference.asHeaderItem());

        return dependencies;
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
