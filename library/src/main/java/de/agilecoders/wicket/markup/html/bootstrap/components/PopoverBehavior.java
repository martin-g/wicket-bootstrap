package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.markup.html.bootstrap.components.PopoverBehavior.PopoverJqueryFunction.popover;
import static de.agilecoders.wicket.util.JQuery.$;

/**
 * Add small overlays of content, like those on the iPad, to any element for housing secondary information.
 * Hover over the button to trigger the popover.
 *
 * @author miha
 * @version 1.0
 */
public class PopoverBehavior extends TooltipBehavior {

    private final IModel<String> body;

    /**
     * Construct.
     *
     * @param label Title of the popover
     */
    public PopoverBehavior(final IModel<String> label) {
        this(label, null);
    }

    /**
     * Construct.
     *
     * @param label Title of the popover
     * @param body  Body of the popover
     */
    public PopoverBehavior(final IModel<String> label, final IModel<String> body) {
        this(label, body, new TooltipConfig().withPlacement(TooltipConfig.Placement.right).withTrigger(TooltipConfig.Trigger.click));
    }

    /**
     * Construct.
     *
     * @param label  Title of the popover
     * @param body   Body of the popover
     * @param config The popover configuration
     */
    public PopoverBehavior(final IModel<String> label, final IModel<String> body, final TooltipConfig config) {
        super(label, config);

        this.body = body;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(AttributeModifier.replace("data-content", body));
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        body.detach();
    }

    @Override
    protected String createRelAttribute() {
        return "popover";
    }

    @Override
    protected CharSequence createInitializerScript(final Component component, final TooltipConfig config) {
        return $(component).chain(popover(config)).get();
    }

    /**
     * A simple popover jquery function representation in java.
     */
    public static final class PopoverJqueryFunction extends JQuery.AbstractFunction {

        /**
         * helper method.
         *
         * @param config tooltip configuration
         */
        public static PopoverJqueryFunction popover(final TooltipConfig config) {
            return new PopoverJqueryFunction(config);
        }

        /**
         * Construct.
         *
         * @param config popover configuration
         */
        private PopoverJqueryFunction(final TooltipConfig config) {
            super("popover");

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }

}
