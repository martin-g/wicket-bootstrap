package de.agilecoders.wicket.markup.html.references;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * DatePicker css resource reference
 *
 * @author miha
 */
public class BootstrapDatepickerReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new BootstrapDatepickerReference();

    /**
     * Private constructor.
     */
    private BootstrapDatepickerReference() {
        super(BootstrapDatepickerReference.class, "css/datepicker.css");
    }
}
