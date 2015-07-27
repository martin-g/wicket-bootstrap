package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.ArrayList;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

public class SummernoteEditorJavaScriptReference extends JavaScriptResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SummernoteEditorJavaScriptReference INSTANCE = new SummernoteEditorJavaScriptReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SummernoteEditorJavaScriptReference instance() {
	return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private SummernoteEditorJavaScriptReference() {
	super(SummernoteEditorJavaScriptReference.class, "js/summernote.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
	ArrayList<HeaderItem> dependencies = new ArrayList<HeaderItem>();
	dependencies.add(JavaScriptHeaderItem.forReference(JQueryResourceReference.get()));
	return dependencies;
    }
}
