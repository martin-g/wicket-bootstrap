package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

import java.util.List;

/**
 * Represents a reference to the JQuery UI JavaScript resource
 */
public class JQueryUIJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryUIJavaScriptReference INSTANCE = new JQueryUIJavaScriptReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUIJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    JQueryUIJavaScriptReference() {
        super("jquery-ui/current/jquery-ui.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                                    JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
