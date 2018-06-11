package de.agilecoders.wicket.core.markup.html.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * Represents Popper.js library.
 *
 * @author Jan Ferko
 */
public class PopperJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final PopperJavaScriptReference INSTANCE = new PopperJavaScriptReference();
    }

    /**
     * Private constructor.
     */
    private PopperJavaScriptReference() {
        super("popper.js/current/umd/popper.js");
    }

    /**
     *
     * @return the single instance of the resource reference
     */
    public static PopperJavaScriptReference instance() {
        return Holder.INSTANCE;
    }
}
