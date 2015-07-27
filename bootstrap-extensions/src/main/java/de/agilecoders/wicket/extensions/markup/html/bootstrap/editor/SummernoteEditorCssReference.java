package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.request.resource.CssResourceReference;

public class SummernoteEditorCssReference extends CssResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SummernoteEditorCssReference INSTANCE = new SummernoteEditorCssReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SummernoteEditorCssReference instance() {
	return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private SummernoteEditorCssReference() {
	super(SummernoteEditorCssReference.class, "css/summernote.css");
    }
}
