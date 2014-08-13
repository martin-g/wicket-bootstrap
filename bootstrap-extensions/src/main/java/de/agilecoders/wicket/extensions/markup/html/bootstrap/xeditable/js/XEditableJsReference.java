package de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable.js;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

public class XEditableJsReference extends WebjarsJavaScriptResourceReference {

    public static final XEditableJsReference INSTANCE = new XEditableJsReference();

    private XEditableJsReference() {
        super("x-editable-bootstrap/1.5.1/js/bootstrap-editable.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
    }
}
