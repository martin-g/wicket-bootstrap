package de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.Bootstrap;

/**
 * Adds the prettify javascript resource to the document's head.
 *
 * @author miha
 * @version 1.0
 */
public class PrettifyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new PrettifyJavaScriptReference();

    /**
     * Private constructor.
     */
    private PrettifyJavaScriptReference() {
        super(PrettifyJavaScriptReference.class, "prettify.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = new ArrayList<>(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
        dependencies.add(CssHeaderItem.forReference(PrettifyCssResourceReference.INSTANCE));

        return dependencies;
    }
}
