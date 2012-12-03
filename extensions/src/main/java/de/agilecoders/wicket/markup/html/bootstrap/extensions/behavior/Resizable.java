package de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.ResizableCssReference;
import de.agilecoders.wicket.util.JQuery;
import de.agilecoders.wicket.util.References;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import static de.agilecoders.wicket.util.JQuery.$;

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
        headerResponse.render(OnDomReadyHeaderItem.forScript($(component).chain("resizable").get()));
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    /**
     * A simple resizable jquery ui function representation in java.
     */
    public static final class ResizableJqueryFunction extends JQuery.AbstractFunction {

        /**
         * helper method.
         *
         * @param config resizable configuration
         */
        public static ResizableJqueryFunction resizable(final AbstractConfig config) {
            return new ResizableJqueryFunction(config);
        }

        /**
         * Construct.
         *
         * @param config draggable configuration
         */
        private ResizableJqueryFunction(final AbstractConfig config) {
            super("resizable");

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }
}
