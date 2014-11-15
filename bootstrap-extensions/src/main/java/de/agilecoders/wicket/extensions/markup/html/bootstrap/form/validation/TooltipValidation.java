package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * <h1>Validation behavior.</h1>
 * <p>Display errors in tooltips.</p>
 * This behavior may not work properly with widgets like bootstrap-select or select2.
 * In this case try to wrap all inputs in div (that's done by default in bootstrap form layout)
 * and set appendToParent of config to true.
 * <br/>
 * <br/>
 * <b>DO NOT USE SimpleMessageValidation behavior and TooltipValidation behavior on one page.</b>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 * @since 15.09.2014
 */
public class TooltipValidation extends ValidationBehavior<TooltipValidationConfig> {

    private static final long serialVersionUID = 1L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        BootstrapResourcesBehavior.instance().renderHead(Bootstrap.getSettings(), response);
        response.render(JavaScriptHeaderItem.forReference(ValidationJS.tooltip()));
        super.renderHead(component, response);
    }

    @Override
    protected TooltipValidationConfig newConfig() {
        return new TooltipValidationConfig().setPlacement("right");
    }

}
