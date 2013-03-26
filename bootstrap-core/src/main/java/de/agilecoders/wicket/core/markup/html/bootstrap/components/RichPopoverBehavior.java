package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * A rich popover implementation that uses a component as body.
 *
 * @author miha
 */
abstract class RichPopoverBehavior extends PopoverBehavior {

    /**
     * Construct.
     *
     * @param label popover title
     */
    private RichPopoverBehavior(final IModel<String> label) {
        this(label, new PopoverConfig());
    }

    /**
     * Construct.
     *
     * @param label  popover title
     * @param config popover configuration
     */
    private RichPopoverBehavior(final IModel<String> label, final PopoverConfig config) {
        super(label, null, config);

        config.withHtml(true);
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AttributeModifier("data-content", newContent()));
    }

    @Override
    protected final IModel<String> newContent() {
        // activate after wicket 6.7.0 was released
        //final String content = String.valueOf(ComponentRenderer.renderComponent(newBodyComponent(ComponentRenderer.COMP_ID)));

        //return Model.of(escapeJava(chomp(content)));
        throw new UnsupportedOperationException("this component is not active");
    }

    /**
     * creates a new popover body component
     *
     * @param markupId the markup id that the body component must be use
     * @return new body component
     */
    public abstract Component newBodyComponent(final String markupId);
}
