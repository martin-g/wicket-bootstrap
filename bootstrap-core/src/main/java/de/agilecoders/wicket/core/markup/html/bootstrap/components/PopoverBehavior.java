package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import static de.agilecoders.wicket.jquery.JQuery.markupId;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.jquery.Config;
import de.agilecoders.wicket.jquery.function.Function;

/**
 * Add small overlays of content, like those on the iPad, to any element for housing secondary information.
 * Hover over the button to trigger the popover.
 *
 * @author miha
 */
public class PopoverBehavior extends TooltipBehavior {

    private final IModel<String> body;

    /**
     * Construct.
     *
     * @param label Title of the popover
     * @param body  Body of the popover
     */
    public PopoverBehavior(final IModel<String> label, final IModel<String> body) {
        this(label, body, new PopoverConfig());
    }

    /**
     * Construct.
     *
     * @param label  Title of the popover
     * @param body   Body of the popover
     * @param config The popover configuration
     */
    public PopoverBehavior(final IModel<String> label, final IModel<String> body, final PopoverConfig config) {
        super(label, config);

        this.body = body;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        tag.put("data-bs-content", newContent());
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        if (body != null) {
            body.detach();
        }
    }

    @Override
    protected String createRelAttribute() {
        return "popover";
    }

    /**
     * @return the popover content
     */
    protected String newContent() {
        return body.getObject();
    }

    @Override
    protected CharSequence createInitializerScript(final Component component, final Config config) {
        return new Function("new bootstrap.Popover", markupId(component).quoted(), config).build();
    }

}
