package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

/**
 * A {@code LoadingBehavior} that deactivates a button and shows a
 * loading message.
 *
 * @author miha
 * @version 1.0
 */
public class LoadingBehavior extends BootstrapJavascriptBehavior {

    private final IModel<String> model;
    private Component component;

    /**
     * Construct.
     *
     * @param model The loading message
     */
    public LoadingBehavior(IModel<String> model) {
        this.model = model;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
        component.add(new AttributeAppender("onclick", "$('#" + component.getMarkupId() + "').button('loading');"));

        this.component = component;
    }

    @Override
    public void unbind(Component component) {
        super.unbind(component);

        this.component = null;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        model.detach();
    }

    /**
     * appends a javascript that resets the loading state.
     *
     * @param component         The button component
     * @param ajaxRequestTarget The {@link AjaxRequestTarget}
     */
    public static void reset(Component component, AjaxRequestTarget ajaxRequestTarget) {
        Preconditions.checkNotNull(component);
        Preconditions.checkNotNull(ajaxRequestTarget);

        ajaxRequestTarget.appendJavaScript("$('#" + component.getMarkupId() + "').button('reset');");
    }

    /**
     * appends a javascript that resets the loading state.
     *
     * @param ajaxRequestTarget The {@link AjaxRequestTarget}
     */
    public void reset(AjaxRequestTarget ajaxRequestTarget) {
        if (component != null) {
            reset(component, ajaxRequestTarget);
        }
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new AttributeModifier("data-loading-text", model));
    }
}
