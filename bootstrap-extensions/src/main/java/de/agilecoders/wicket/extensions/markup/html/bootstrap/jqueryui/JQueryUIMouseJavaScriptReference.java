package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import java.util.List;

/**
 * JavaScript resource reference for jqueryui.mouse.js
 */
public class JQueryUIMouseJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryUIMouseJavaScriptReference INSTANCE = new JQueryUIMouseJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUIMouseJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private JQueryUIMouseJavaScriptReference() {
        super("jquery-ui/current/ui/minified/jquery.ui.mouse.min.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
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
