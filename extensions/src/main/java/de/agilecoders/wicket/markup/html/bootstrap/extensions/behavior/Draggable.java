package de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import static de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.Draggable.DraggableJqueryFunction.draggable;
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
        headerResponse.render(OnDomReadyHeaderItem.forScript($(component).chain(draggable(config)).get()));
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    /**
     * A simple draggable jquery ui function representation in java.
     */
    public static final class DraggableJqueryFunction extends JQuery.AbstractFunction {

        /**
         * helper method.
         *
         * @param config tooltip configuration
         */
        public static DraggableJqueryFunction draggable(final AbstractConfig config) {
            return new DraggableJqueryFunction(config);
        }

        /**
         * Construct.
         *
         * @param config draggable configuration
         */
        private DraggableJqueryFunction(final AbstractConfig config) {
            super("draggable");

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }
}
