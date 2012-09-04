package de.agilecoders.wicket.markup.html.bootstrap.block.prettyprint;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.Bootstrap;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class PrettyJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    public static final String ID = "prettify-js";

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
    public Iterable<? extends HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));

        return dependencies;
    }
}