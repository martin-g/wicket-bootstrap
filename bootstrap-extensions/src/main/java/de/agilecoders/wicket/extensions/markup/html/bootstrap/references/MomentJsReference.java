package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * WebJar reference to moment js
 *
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class MomentJsReference extends WebjarsJavaScriptResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final MomentJsReference INSTANCE = new MomentJsReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static MomentJsReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private MomentJsReference() {
        super("moment/current/min/moment.min.js");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
