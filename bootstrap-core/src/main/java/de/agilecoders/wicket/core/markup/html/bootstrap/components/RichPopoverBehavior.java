package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.core.util.Strings2.nullToEmpty;
import static org.apache.commons.lang3.StringUtils.chomp;

/**
 * A rich popover implementation that uses a component as body.
 *
 * @author miha
 */
public abstract class RichPopoverBehavior extends PopoverBehavior {

    /**
     * Construct.
     *
     * @param label popover title
     */
    public RichPopoverBehavior(final IModel<String> label) {
        this(label, new PopoverConfig());
    }

    /**
     * Construct.
     *
     * @param label  popover title
     * @param config popover configuration
     */
    public RichPopoverBehavior(final IModel<String> label, final PopoverConfig config) {
        super(label, null, config);

        config.withHtml(true);
    }

    @Override
    protected final String newContent() {
        final String content = String.valueOf(ComponentRenderer.renderComponent(newBodyComponent("compId")));

        return cleanContent(content);
    }

    /**
     * cleans the final markup
     *
     * @param content the markup to clean up
     * @return cleaned markup.
     */
    protected String cleanContent(final String content) {
        return chomp(nullToEmpty(content)).replaceAll("\"", "\\\"");
    }

    /**
     * creates a new popover body component
     *
     * @param markupId the markup id that the body component must be use
     * @return new body component
     */
    public abstract Component newBodyComponent(final String markupId);
}
