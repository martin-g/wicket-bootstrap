package de.agilecoders.wicket.extensions.markup.html.bootstrap.confirmation;

import de.agilecoders.wicket.core.util.References;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A behavior that shows a popover with OK/Cancel buttons to confirm an action.
 * @since 0.9.12
 */
public class ConfirmationBehavior extends Behavior {

    private final ConfirmationConfig config;

    /**
     * Constructor that uses the default configuration
     */
    public ConfirmationBehavior() {
        this(new ConfirmationConfig());
    }

    /**
     * Constructor that uses a custom configuration
     */
    public ConfirmationBehavior(ConfirmationConfig config) {
        this.config = Args.notNull(config, "config");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        References.renderWithFilter(response, JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(ConfirmationBehavior.class, "bootstrap-confirmation.js")));
        response.render($(component).chain("confirmation", config).asDomReadyScript());
    }
}
