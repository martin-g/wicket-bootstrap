package de.agilecoders.wicket.markup.html.themes.metro;

import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MetroCssReference extends BootstrapCssReference {
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