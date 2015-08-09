package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public class SummernoteEditorJavaScriptReference extends JQueryPluginResourceReference {

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
}
