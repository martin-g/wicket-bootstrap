package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.util.Dependencies;

public final class FileinputJsReference extends JavaScriptResourceReference implements IHeaderContributor {
    private static final long serialVersionUID = 1L;
    public static final FileinputJsReference INSTANCE = new FileinputJsReference();

    private FileinputJsReference() {
        super(FileinputJsReference.class, "res/js/fileinput.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        
        IBootstrapSettings settings = Bootstrap.getSettings(Application.get());
        return Dependencies.combine(
                super.getDependencies(),
                JavaScriptReferenceHeaderItem.forReference(settings.getJsResourceReference()),
                CssHeaderItem.forReference(FileinputCssReference.INSTANCE),
                JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}
