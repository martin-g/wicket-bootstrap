package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;
import static org.apache.commons.lang.StringUtils.chomp;

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
        return new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                final String content = String.valueOf(ComponentRenderer.renderComponent(newBodyComponent(ComponentRenderer.COMP_ID)));

                return escapeJava(chomp(content));
            }
        };
    }

    /**
     * creates a new popover body component
     *
     * @param markupId the markup id that the body component must be use
     * @return new body component
     */
    public abstract Component newBodyComponent(final String markupId);
}
