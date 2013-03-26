package de.agilecoders.wicket.samples.assets.base;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A simple stylesheet to fix some styles for the demo page.
 *
 * @author miha
 * @version 1.0
 */
public class FixBootstrapStylesCssResourceReference extends CssResourceReference {

    public static final FixBootstrapStylesCssResourceReference INSTANCE = new FixBootstrapStylesCssResourceReference();

    /**
     * Construct.
     */
    public FixBootstrapStylesCssResourceReference() {
        super(FixBootstrapStylesCssResourceReference.class, "fix.css");
    }
}
