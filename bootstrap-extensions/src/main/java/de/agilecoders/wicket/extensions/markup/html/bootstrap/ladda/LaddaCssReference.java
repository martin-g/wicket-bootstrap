package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A CSS resource reference that contributes ladda.css.
 */
public class LaddaCssReference extends CssResourceReference {

    public static final LaddaCssReference INSTANCE = new LaddaCssReference();

    public LaddaCssReference() {
        super(LaddaCssReference.class, "css/ladda-themeless.css");
    }
}
