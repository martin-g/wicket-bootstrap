package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * @author Alexey Volkov
 * @since 02.11.14
 */
public class SelectJSReference extends WebjarsJavaScriptResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SelectJSReference INSTANCE = new SelectJSReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SelectJSReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private SelectJSReference() {
        super("bootstrap-select/current/dist/js/bootstrap-select.js");
    }
}
