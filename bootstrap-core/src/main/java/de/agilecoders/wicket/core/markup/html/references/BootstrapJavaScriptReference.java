package de.agilecoders.wicket.core.markup.html.references;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * represents the bootstrap js library
 *
 * @author miha
 */
public class BootstrapJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final BootstrapJavaScriptReference INSTANCE = new BootstrapJavaScriptReference();
    }

    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getJsResourceReference()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static BootstrapJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private BootstrapJavaScriptReference() {
        super("/bootstrap/current/js/bootstrap.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                                    JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getPopperJsResourceReference()));
    }
}
