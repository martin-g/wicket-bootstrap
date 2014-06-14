package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileinput.release;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;

@Immutable
public final class FileinputJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final FileinputJsReference INSTANCE = new FileinputJsReference();

    private FileinputJsReference() {
        super(FileinputJsReference.class, "fileinput.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(
                super.getDependencies(),
                JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }

}
