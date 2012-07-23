package de.agilecoders.wicket.markup.html.references;

import com.google.common.collect.Lists;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ModernizrJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;
    private static final String NAME = "js/modernizr-2.5.3.min.js";

    /**
     * Singleton instance of this reference
     */
    public static final ResourceReference INSTANCE = new ModernizrJavaScriptReference();

    /**
     * Private constructor.
     */
    private ModernizrJavaScriptReference() {
        super(ModernizrJavaScriptReference.class, NAME);
    }

    @Override
    protected String getMinifiedName() {
        return NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }

}