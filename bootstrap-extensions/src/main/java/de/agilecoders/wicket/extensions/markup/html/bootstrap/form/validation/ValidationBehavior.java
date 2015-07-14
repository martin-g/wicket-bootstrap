package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.ArrayList;
import java.util.List;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * <h1>Validation behavior.</h1>
 * <p>Display error messages on form.</p>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 * @see FeedbackMessageBehaviorVisitor
 * @since 15.09.2014
 */
public abstract class ValidationBehavior<T extends AbstractValidationConfig> extends Behavior {

    private static final long serialVersionUID = 1L;

    private static final String attribute = "wb-validation-message";

    /**
     * config
     */
    protected final T config;

    public ValidationBehavior(T config) {
        this.config = Args.notNull(config, "config");
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        if (component instanceof MarkupContainer) {
            FeedbackMessageBehaviorVisitor.add((MarkupContainer) component, attribute);
        }
    }

    @Override
    public void onEvent(Component component, IEvent<?> event) {
        if (event.getPayload() instanceof IPartialPageRequestHandler) {
            IPartialPageRequestHandler target = (IPartialPageRequestHandler) event.getPayload();
            if (component instanceof Form<?>) {
                attachJavaScript(target, component);
            } else if (component instanceof MarkupContainer) {
                for (Form<?> form : getChildren((MarkupContainer) component, Form.class)) {
                    attachJavaScript(target, form);
                }
            }
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.common()));
        response.render(OnDomReadyHeaderItem.forScript("$.wb_validation.updateConfig(" + getConfig().toJsonString() + ");"));
        if (component instanceof Form<?>) {
            renderJavaScript(response, component);
        } else if (component instanceof MarkupContainer) {
            for (Form<?> form : getChildren((MarkupContainer) component, Form.class)) {
                renderJavaScript(response, form);
            }
        }

    }

    /**
     * @return get config instance
     */
    public T getConfig() {
        return config;
    }

    private void attachJavaScript(IPartialPageRequestHandler target, Component component) {
        target.appendJavaScript($(component).chain("wb_validation").get());
    }

    private void renderJavaScript(IHeaderResponse response, Component component) {
        response.render($(component).chain(ParametrizedFunction.func("wb_validation", "validate"))
            .asDomReadyScript());
    }

    private <T extends Component> List<T> getChildren(MarkupContainer container, final Class<T> clazz) {
        final List<T> result = new ArrayList<T>();
        IVisitor<Component, T> visitor = new IVisitor<Component, T>() {

            @Override
            public void component(Component object, IVisit<T> visit) {
                result.add(clazz.cast(object));
            }
        };
        container.visitChildren(clazz, visitor);
        return result;
    }
}
