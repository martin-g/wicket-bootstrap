package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.References;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.ResizableCssReference;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * Makes an assigned component draggable.
 *
 * @author miha
 */
public class Resizable extends BootstrapBaseBehavior {

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(CssHeaderItem.forReference(ResizableCssReference.instance()));
        References.renderWithFilter(headerResponse, newResizableResourceReference());
        headerResponse.render($(component).chain("resizable").asDomReadyScript());
    }

    /**
     * @return new draggable js resource reference
     */
    protected JavaScriptResourceReference newResizableResourceReference() {
        return JQueryUIResizableJavaScriptReference.instance();
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

}
