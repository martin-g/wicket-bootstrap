package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.References;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.ResizableCssReference;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

import static de.agilecoders.wicket.core.util.JQuery.$;

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
        References.renderWithFilter(headerResponse, JQueryUIJavaScriptReference.instance());
        headerResponse.render($(component).chain("resizable").asDomReadyScript());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

}
