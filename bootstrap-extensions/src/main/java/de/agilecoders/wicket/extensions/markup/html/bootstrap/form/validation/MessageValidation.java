package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * <h1>Validation behavior.</h1>
 * <p>Display error messages on form.</p>
 *
 * @author Alexey Volkov
 * @see FormComponentVisitor
 * @since 15.09.2014
 */
public class MessageValidation extends ValidationBehavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.common()));
    }
}
