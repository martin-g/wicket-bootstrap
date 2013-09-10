package de.agilecoders.wicket.extensions.javascript.jasny;

import de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A JavaScript reference that loads the JavaScript resources needed by
 * Jasny Twitter Bootstrap components.
 */
public class JasnyJsReference extends JQueryPluginResourceReference
{
    public static final JasnyJsReference INSTANCE = new JasnyJsReference();

    private JasnyJsReference()
    {
        super(JasnyJsReference.class, "js/bootstrap.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies()
    {
        List<HeaderItem> deps = new ArrayList<HeaderItem>();
        for (HeaderItem dep : super.getDependencies()) {
            deps.add(dep);
        }
        deps.add(CssHeaderItem.forReference(JasnyCssReference.INSTANCE));
        deps.add(JavaScriptHeaderItem.forReference(BootstrapJavaScriptReference.instance()));
        return deps;
    }
}
