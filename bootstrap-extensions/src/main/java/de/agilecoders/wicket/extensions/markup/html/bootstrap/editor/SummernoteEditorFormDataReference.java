package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A workaround to provide FormData for IE>10
 * 
 * @author Tobias Soloschenko
 */
public class SummernoteEditorFormDataReference extends JavaScriptResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SummernoteEditorFormDataReference INSTANCE = new SummernoteEditorFormDataReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SummernoteEditorFormDataReference instance() {
	return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private SummernoteEditorFormDataReference() {
	super(SummernoteEditor.class, "js/summernote_formdata.js");
    }
}
