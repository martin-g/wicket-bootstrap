package de.agilecoders.wicket.extensions.markup.html.bootstrap.button;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * A {@link org.apache.wicket.request.resource.JavaScriptResourceReference} that includes the dropdown
 * auto open script.
 *
 * @author miha
 */
public class DropdownAutoOpenJavaScriptReference extends JQueryPluginResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final DropdownAutoOpenJavaScriptReference INSTANCE = new DropdownAutoOpenJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static DropdownAutoOpenJavaScriptReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private DropdownAutoOpenJavaScriptReference() {
        super(DropdownAutoOpenJavaScriptReference.class, "dropdown-autoopen.js");
    }
}
