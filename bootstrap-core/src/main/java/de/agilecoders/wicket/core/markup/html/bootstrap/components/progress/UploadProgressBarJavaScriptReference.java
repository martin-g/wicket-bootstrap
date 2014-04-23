package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import de.agilecoders.wicket.core.util.Dependencies;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.List;

/**
 * Provides a JavaScript that overrides Wicket-Extensions' progressbar.js
 * methods with ones which are more suitable for Twitter Bootstrap Progress component
 */
public class UploadProgressBarJavaScriptReference extends JQueryPluginResourceReference {

    public UploadProgressBarJavaScriptReference() {
        super(UploadProgressBarJavaScriptReference.class, "progressbar.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(
                org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar.class, "progressbar.js"));

        return Dependencies.combine(super.getDependencies(), headerItem);
    }
}
