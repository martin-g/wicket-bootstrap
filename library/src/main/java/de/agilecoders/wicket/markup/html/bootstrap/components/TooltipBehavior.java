package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.markup.html.bootstrap.components.TooltipBehavior.TooltipJqueryFunction.tooltip;
import static de.agilecoders.wicket.util.JQuery.$;

/**
 * Tooltips are an updated version of Jquery.tipsy, which don't rely on images,
 * use CSS3 for animations, and data-attributes for local title storage.
 *
 * @author miha
 * @version 1.0
 */
public class TooltipBehavior extends BootstrapJavascriptBehavior {

    private final IModel<String> label;
    private final TooltipConfig config;

    /**
     * Construct.
     *
     * @param label The tooltip text
     */
    public TooltipBehavior(final IModel<String> label) {
        this(label, new TooltipConfig());
    }

    /**
     * Construct.
     *
     * @param label  The tooltip text
     * @param config configuration of tooltip
     */
    public TooltipBehavior(final IModel<String> label, final TooltipConfig config) {
        this.label = label;
        this.config = config;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        label.detach();
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
        component.add(AttributeModifier.replace("rel", createRelAttribute()));
        component.add(AttributeModifier.replace("title", label));
    }

    /**
     * @return the value of the "rel" attribute
     */
    protected String createRelAttribute() {
        return "tooltip";
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(OnDomReadyHeaderItem.forScript(createInitializerScript(component, config)));
    }

    /**
     * createss an initializer script
     *
     * @param component The component where this behavior is assigned to.
     * @param config    The current configuration
     * @return new initializer script
     */
    protected CharSequence createInitializerScript(final Component component, final TooltipConfig config) {
        return $(component).chain(tooltip(config)).get();
    }

    /**
     * A simple tooltip jquery function representation in java.
     */
    public static final class TooltipJqueryFunction extends JQuery.AbstractFunction {

        /**
         * helper method.
         *
         * @param config tooltip configuration
         */
        public static TooltipJqueryFunction tooltip(final TooltipConfig config) {
            return new TooltipJqueryFunction(config);
        }

        /**
         * Construct.
         *
         * @param config tooltip configuration
         */
        private TooltipJqueryFunction(final TooltipConfig config) {
            super("tooltip");

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }
}
