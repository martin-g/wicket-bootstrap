package de.agilecoders.wicket.themes.markup.html.metro;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Arrays;
import java.util.List;

/**
 * A windows 8 inspired theme
 *
 * @author miha
 */
public class MetroCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * @return singleton instance of {@link MetroCssReference}
     */
    public static MetroCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final MetroCssReference INSTANCE = new MetroCssReference();
    }

    /**
     * Private constructor.
     */
    private MetroCssReference() {
        super(MetroCssReference.class, "css/m8tro.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        ResourceReference cssResourceReference;
        if (Application.exists()) {
            cssResourceReference = Bootstrap.getSettings().getCssResourceReference();
        } else {
            cssResourceReference = BootstrapCssReference.instance();
        }
        return Arrays.<HeaderItem>asList(CssHeaderItem.forReference(cssResourceReference));
    }
}
