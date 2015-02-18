package de.agilecoders.wicket.extensions.javascript.jasny;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.List;

/**
 * A JavaScript reference that loads the JavaScript resources needed by
 * Jasny Bootstrap components.
 */
public class JasnyJsReference extends JQueryPluginResourceReference {

    private static final long serialVersionUID = 1L;

    private static final class Holder {

        private static final JasnyJsReference INSTANCE = new JasnyJsReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static JasnyJsReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Construct.
     */
    private JasnyJsReference() {
        super(JasnyJsReference.class, "js/bootstrap.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
            // omit css resource reference because it breaks theme provider.
            // CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()),
            JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
    }
}
