package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import org.apache.wicket.request.resource.CssResourceReference;

class FileinputCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;
    public static final FileinputCssReference INSTANCE = new FileinputCssReference();

    private FileinputCssReference() {
        super(FileinputCssReference.class, "res/css/fileinput.css");
    }
}
