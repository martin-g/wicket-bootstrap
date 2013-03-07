package de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.JQueryUIJavaScriptReference;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import static de.agilecoders.wicket.util.JQuery.$;

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

        headerResponse.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptReference.instance()));
        headerResponse.render($(component).chain("draggable", config).asDomReadyScript());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

}
