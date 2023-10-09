package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * Eonasdan tempus-dominus js reference
 *
 */
public class TempusDominusJsReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final TempusDominusJsReference INSTANCE = new TempusDominusJsReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static TempusDominusJsReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private TempusDominusJsReference() {
        super(TempusDominusJsReference.class, "js/wicket.tempus-dominus.js");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return JavaScriptHeaderItem.forReference(instance());
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("eonasdan__tempus-dominus/current/dist/js/tempus-dominus.js")));
    }
}
