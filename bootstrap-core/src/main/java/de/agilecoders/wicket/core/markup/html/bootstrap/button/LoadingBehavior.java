package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import static de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonJqueryFunction.button;
import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A {@code LoadingBehavior} that deactivates a button and shows a
 * loading message.
 *
 * @author miha
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
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        tag.put("onclick", $(component).chain(button("loading")).get());
        tag.put("data-loading-text", model.getObject());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);

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
     * @param target The {@link IPartialPageRequestHandler}
     */
    public static void reset(final Component component, final IPartialPageRequestHandler target) {
        Args.notNull(component, "component");
        Args.notNull(target, "target");

        target.appendJavaScript($(component).chain(button("reset")).get());
    }

    /**
     * appends a javascript that resets the loading state.
     *
     * @param target The {@link IPartialPageRequestHandler}
     */
    public void reset(final IPartialPageRequestHandler target) {
        if (component != null) {
            reset(component, target);
        }
    }

}
