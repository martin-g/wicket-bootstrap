package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * <h1>Validation behavior.</h1>
 * <p>Display error messages on form.</p>
 * This behavior may not work properly with widgets like bootstrap-select or select2.
 * In this case try to wrap all inputs in div (that's done by default in bootstrap form layout)
 * and set appendToParent of config to true.
 * <br/>
 * <br/>
 * <b>DO NOT USE SimpleMessageValidation behavior and TooltipValidation behavior on one page.</b>
 *
 * @author Alexey Volkov
 * @see FeedbackMessageBehaviorVisitor
 * @since 15.09.2014
 */
public class SimpleMessageValidation extends ValidationBehavior<SimpleValidationConfig> {

    private static final long serialVersionUID = 1L;

    public SimpleMessageValidation() {
        this(new SimpleValidationConfig());
    }

    public SimpleMessageValidation(SimpleValidationConfig config) {
        super(config);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.simple()));
        super.renderHead(component, response);
    }
}
