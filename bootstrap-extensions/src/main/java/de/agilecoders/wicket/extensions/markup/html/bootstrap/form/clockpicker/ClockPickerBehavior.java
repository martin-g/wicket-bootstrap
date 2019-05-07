package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.clockpicker;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.core.util.References;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

import static de.agilecoders.wicket.jquery.JQuery.$;


public class ClockPickerBehavior extends BootstrapJavascriptBehavior {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /** Configuration. */
    private final ClockPickerConfig config;

    /**
     * Constructor that uses the default configuration
     */
    public ClockPickerBehavior() {
        this(new ClockPickerConfig());
    }

    /**
     * Constructor that uses a custom configuration
     * @param config configuration to use
     */
    public ClockPickerBehavior(ClockPickerConfig config) {
        this.config = Args.notNull(config, "config");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        References.renderWithFilter(response, JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(ClockPickerBehavior.class, "bootstrap-clockpicker.js")));
        response.render(CssHeaderItem.forReference(new CssResourceReference(ClockPickerBehavior.class, "bootstrap-clockpicker.css")));

        response.render($(component).chain("clockpicker", config).asDomReadyScript());
    }
}
