package de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A JavaScript resource reference that contributes ladda.js.
 */
public class SpinnerJsReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;
    public static final SpinnerJsReference INSTANCE = new SpinnerJsReference();

    public SpinnerJsReference() {
        super(SpinnerJsReference.class, "js/ladda.js");
    }
}
