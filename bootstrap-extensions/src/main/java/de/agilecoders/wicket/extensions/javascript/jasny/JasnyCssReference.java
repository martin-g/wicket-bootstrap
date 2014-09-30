package de.agilecoders.wicket.extensions.javascript.jasny;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A CSS reference that loads the CSS resources needed by
 * Jasny Bootstrap components.
 */
public class JasnyCssReference extends CssResourceReference
{
    public static final JasnyCssReference INSTANCE = new JasnyCssReference();

    private JasnyCssReference()
    {
        super(JasnyCssReference.class, "css/bootstrap.css");
    }
}
