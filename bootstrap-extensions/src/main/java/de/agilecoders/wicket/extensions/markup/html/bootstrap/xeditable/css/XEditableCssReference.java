package de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable.css;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

public class XEditableCssReference extends WebjarsCssResourceReference {

    public static final XEditableCssReference INSTANCE = new XEditableCssReference("x-editable-bootstrap/1.5.1/css/bootstrap-editable.css");

    private XEditableCssReference(String name) {
        super(name);
    }

}