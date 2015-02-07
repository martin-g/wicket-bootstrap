package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * WebJar reference to moment with locales js
 *
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class MomentWithLocalesJsReference extends WebjarsJavaScriptResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final MomentWithLocalesJsReference INSTANCE = new MomentWithLocalesJsReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static MomentWithLocalesJsReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private MomentWithLocalesJsReference() {
        super("momentjs/current/min/moment-with-locales.min.js");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }
}
