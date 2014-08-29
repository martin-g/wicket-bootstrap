package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A JavaScript resource reference that contributes spin.js.
 */
public class SpinJsReference extends JavaScriptResourceReference {

    public static final SpinJsReference INSTANCE = new SpinJsReference();

    public SpinJsReference() {
        super(SpinJsReference.class, "js/spin.js");
    }
}
