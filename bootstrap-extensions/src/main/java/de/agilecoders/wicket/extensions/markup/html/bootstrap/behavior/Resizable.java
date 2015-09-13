package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.References;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * Makes an assigned component draggable.
 *
 * @author miha
 */
public class Resizable extends BootstrapBaseBehavior {

    private CharSequence childSelector;

    /**
     * Sets a selector for a child element of the associated component' component tag
     * that should be used for resizing
     *
     * @param childSelector The selector for the child element
     * @return {@code this} instance, for chaining
     */
    public Resizable withChildSelector(CharSequence childSelector) {
        this.childSelector = childSelector;
        return this;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(CssHeaderItem.forReference(JQueryUICssReference.instance()));
        References.renderWithFilter(headerResponse, newResizableResourceReference());

        JQuery $el = $(component);
        if (!Strings.isEmpty(childSelector)) {
            $el = $el.find(childSelector);
        }
        OnDomReadyHeaderItem headerItem = $el.chain("resizable").asDomReadyScript();
        headerResponse.render(headerItem);
    }

    /**
     * @return new draggable js resource reference
     */
    protected JavaScriptResourceReference newResizableResourceReference() {
        return JQueryUIJavaScriptReference.instance();
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }
}
