package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.References;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIJavaScriptReference;

/**
 * Makes an assigned component draggable.
 *
 * @author miha
 */
public class Draggable extends BootstrapBaseBehavior {

    private final DraggableConfig config;

    /**
     * Construct.
     */
    public Draggable() {
        this(new DraggableConfig().withCursor("move"));
    }

    /**
     * Construct.
     *
     * @param config The configuration
     */
    public Draggable(final DraggableConfig config) {
        this.config = config;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        References.renderWithFilter(headerResponse, newDraggableResourceReference());
        headerResponse.render($(component).chain("draggable", config).asDomReadyScript());
    }

    /**
     * @return new draggable js resource reference
     */
    protected JavaScriptResourceReference newDraggableResourceReference() {
        return JQueryUIJavaScriptReference.instance();
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

}
