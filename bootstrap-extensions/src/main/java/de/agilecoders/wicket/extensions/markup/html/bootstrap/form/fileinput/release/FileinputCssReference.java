package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.release;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

public final class FileinputCssReference extends CssResourceReference implements IHeaderContributor {

    public static final FileinputCssReference INSTANCE = new FileinputCssReference();

    private FileinputCssReference() {
        super(FileinputCssReference.class, "fileinput.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
