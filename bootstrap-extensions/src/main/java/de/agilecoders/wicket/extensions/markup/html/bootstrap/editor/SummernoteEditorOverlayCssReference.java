package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * The summernote overlay css resource reference
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteEditorOverlayCssReference extends CssResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SummernoteEditorOverlayCssReference INSTANCE = new SummernoteEditorOverlayCssReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SummernoteEditorOverlayCssReference instance() {
	return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private SummernoteEditorOverlayCssReference() {
	super(SummernoteEditorOverlayCssReference.class,"css/summernote.css");
    }
}
