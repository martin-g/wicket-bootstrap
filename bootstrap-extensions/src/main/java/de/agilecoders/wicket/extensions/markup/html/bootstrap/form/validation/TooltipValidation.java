package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

/**
 * <h1>Validation behavior.</h1>
 * <p>Display error tooltips on form.</p>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 * @see FormComponentVisitor
 * @since 15.09.2014
 */
public class TooltipValidation extends ValidationBehavior {

    private static final long serialVersionUID = 1L;
    private TooltipValidationConfig config = new TooltipValidationConfig();

    /**
     * @return current config instance
     */
    public TooltipValidationConfig config() {
        return config;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        BootstrapResourcesBehavior.instance().renderHead(Bootstrap.getSettings(), response);
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.common()));
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.tooltip()));
        config.setPlacement("right");
        response.render(OnDomReadyHeaderItem.forScript("$.wb_validation.config = " + config.toJsonString() + ";"));
        super.renderHead(component, response);
    }
}
