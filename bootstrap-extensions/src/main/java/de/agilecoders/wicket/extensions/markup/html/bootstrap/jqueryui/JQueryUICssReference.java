package de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * Represents a reference to the JQuery UI CSS resource
 */
public class JQueryUICssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final JQueryUICssReference INSTANCE = new JQueryUICssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JQueryUICssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    JQueryUICssReference() {
        super("jquery-ui/current/jquery-ui.css");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return CssHeaderItem.forReference(instance());
    }
}
