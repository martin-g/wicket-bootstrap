package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * The summernote css provided via webjars
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteEditorCssReference extends WebjarsCssResourceReference {

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
	super("summernote/current/dist/summernote-bs5.css");
    }
}
