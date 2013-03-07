package de.agilecoders.wicket.markup.html.themes.metro;

import org.apache.wicket.request.resource.CssResourceReference;

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
        super(MetroCssReference.class, "css/metro.css");
    }

}