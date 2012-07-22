package de.agilecoders.wicket.markup.html.references;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootswatchCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference CERULEAN = new BootswatchCssReference("css/bootstrap.cerulean.css");
    public static final ResourceReference AMELIA = new BootswatchCssReference("css/bootstrap.amelia.css");
    public static final ResourceReference JOURNAL = new BootswatchCssReference("css/bootstrap.journal.css");
    public static final ResourceReference CYBORG = new BootswatchCssReference("css/bootstrap.cyborg.css");
    public static final ResourceReference READABLE = new BootswatchCssReference("css/bootstrap.readable.css");
    public static final ResourceReference SIMPLEX = new BootswatchCssReference("css/bootstrap.simplex.css");
    public static final ResourceReference SLATE = new BootswatchCssReference("css/bootstrap.slate.css");
    public static final ResourceReference SPACELAB = new BootswatchCssReference("css/bootstrap.spacelab.css");
    public static final ResourceReference SPRUCE = new BootswatchCssReference("css/bootstrap.spruce.css");
    public static final ResourceReference SUPERHERO = new BootswatchCssReference("css/bootstrap.superhero.css");
    public static final ResourceReference UNITED = new BootswatchCssReference("css/bootstrap.united.css");

    /**
     * Private constructor.
     */
    private BootswatchCssReference(final String name) {
        super(BootswatchCssReference.class, name);
    }

}