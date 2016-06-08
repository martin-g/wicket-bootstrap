package de.agilecoders.wicket.extensions.markup.html.bootstrap.confirmation;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.core.util.References;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A behavior that shows a popover with OK/Cancel buttons to confirm an action.
 * @since 0.9.12
 */
public class ConfirmationBehavior extends BootstrapJavascriptBehavior {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /** Configuration. */
    private final ConfirmationConfig config;
    /** Jquery Selector (if you don't want to use the one of the component for singleton for example). */
    private final String selector;

    /**
     * Constructor that uses the default configuration
     */
    public ConfirmationBehavior() {
        this(null, new ConfirmationConfig());
    }

    /**
     * Constructor that uses a custom configuration
     * @param config configuration to use
     */
    public ConfirmationBehavior(ConfirmationConfig config) {
        this(null, config);
    }

    /**
     * Constructor that uses a custom configuration
     * @param config configuration to use
     * @param selector Jquery selector to use instead of the one of the component (for singleton's option)
     */
    public ConfirmationBehavior(String selector, ConfirmationConfig config) {
        this.config = Args.notNull(config, "config");
        this.selector = selector;
    }
    
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        References.renderWithFilter(response, JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(ConfirmationBehavior.class, "bootstrap-confirmation.js")));
        if (selector == null) {
            response.render($(component).chain("confirmation", config).asDomReadyScript());
        } else {
            response.render($(selector).chain("confirmation", config).asDomReadyScript());
        }
    }
}
