package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

/**
 * Eonasdan tempus-dominus css reference
 *
 */
public class TempusDominusCssReference extends WebjarsCssResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final TempusDominusCssReference INSTANCE = new TempusDominusCssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static TempusDominusCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private TempusDominusCssReference() {
        super("eonasdan__tempus-dominus/current/dist/css/tempus-dominus.css");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return CssHeaderItem.forReference(instance());
    }
}


