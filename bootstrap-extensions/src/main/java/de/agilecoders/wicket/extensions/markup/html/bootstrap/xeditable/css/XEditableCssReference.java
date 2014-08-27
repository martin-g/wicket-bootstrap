package de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable.css;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

public class XEditableCssReference extends WebjarsCssResourceReference {

    public static final XEditableCssReference INSTANCE = new XEditableCssReference();

    private XEditableCssReference() {
        super("x-editable-bootstrap/current/css/bootstrap-editable.css");
    }

}