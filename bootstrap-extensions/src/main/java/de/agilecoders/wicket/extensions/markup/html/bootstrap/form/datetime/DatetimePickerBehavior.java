package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.DatetimePickerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.DatetimePickerJsReference;
import de.agilecoders.wicket.jquery.function.AbstractFunction;
import de.agilecoders.wicket.jquery.function.IFunction;

/**
 * Datetime picker behavior for Eonasdan datetime picker plugin.
 *
 * @author Alexey Volkov
 * @since 01.02.2015
 */
public class DatetimePickerBehavior extends BootstrapJavascriptBehavior {

    private static final long serialVersionUID = 1L;

    private static class DestroyScript extends AbstractFunction {

        private static final long serialVersionUID = 1L;

        private DestroyScript() {
            super("datetimepicker");
            addParameter(toParameterValue("destroy"));
        }
    }

    // destroy script for component
    private static final IFunction destroyScript = new DestroyScript();
    private final DatetimePickerConfig config;

    /**
     * Construct instance
     *
     * @param config config
     */
    public DatetimePickerBehavior(DatetimePickerConfig config) {
        this.config = config;
    }

    @Override
    public void renderHead(Component component, final IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(DatetimePickerCssReference.asHeaderItem());
        response.render(DatetimePickerJsReference.asHeaderItem());
        response.render($(component).chain("datetimepicker", config).asDomReadyScript());
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);
        Attributes.set(tag, "data-target", "#" + component.getMarkupId());
        Attributes.set(tag, "data-toggle", "datetimepicker");
    }

    @Override
    public void onEvent(Component component, IEvent<?> event) {
        super.onEvent(component, event);
        if (event.getPayload() instanceof IPartialPageRequestHandler) {
            IPartialPageRequestHandler target = (IPartialPageRequestHandler) event.getPayload();
            if (target.getComponents().contains(component)) {
                // if this component is being repainted by ajax, directly, we must destroy bootstrap select so it removes
                // its elements from DOM
                target.prependJavaScript($(component).chain(destroyScript).get());
            }
        }
    }

}
