package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * A JavaScript resource reference that contributes spin.js.
 */
public class SpinJsReference extends WebjarsJavaScriptResourceReference {

    public static final SpinJsReference INSTANCE = new SpinJsReference();

    public SpinJsReference() {
        super("spin-js/current/spin.js");
    }
}
