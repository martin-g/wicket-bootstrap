package de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint;

import de.agilecoders.wicket.core.Bootstrap;

import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * Adds the prettify javascript resource to the document's head.
 *
 * @author miha
 * @version 1.0
 */
public class PrettyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new PrettyJavaScriptReference();

    /**
     * Private constructor.
     */
    private PrettyJavaScriptReference() {
        super(PrettyJavaScriptReference.class, "prettify.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = Generics2.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
        dependencies.add(CssHeaderItem.forReference(PrettyCssResourceReference.INSTANCE));

        return dependencies;
    }
}
