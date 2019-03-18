package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.markup.head.HeaderItem;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

/**
 * The summernote javascript provided via webjars
 *
 * @author Tobias Soloschenko
 *
 */
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

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = super.getDependencies();
        ResourceReference jQueryResourceReference;
        if (Application.exists()) {
            jQueryResourceReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
        } else {
            jQueryResourceReference = JQueryResourceReference.getV2();
        }
        dependencies.add(JavaScriptHeaderItem.forReference(jQueryResourceReference));
        return dependencies;
    }
}
