package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;

import java.util.List;

public final class FileinputLocaleJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    FileinputLocaleJsReference(String locale) {
        super(FileinputLocaleJsReference.class, "res/js/locales/" + locale + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(
                super.getDependencies(),
                CssHeaderItem.forReference(FileinputCssReference.INSTANCE),
                JavaScriptHeaderItem.forReference(FileinputJsReference.INSTANCE));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }

}
