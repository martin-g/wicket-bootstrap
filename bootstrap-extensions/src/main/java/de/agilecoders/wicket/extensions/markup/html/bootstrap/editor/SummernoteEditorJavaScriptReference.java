package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

public class SummernoteEditorJavaScriptReference extends WebjarsJavaScriptResourceReference {

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
	super("summernote/current/dist/summernote.js");
    }
}
