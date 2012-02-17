package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import de.agilecoders.wicket.protocol.http.IBootstrapApplication;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResourcesBehavior extends Behavior {

    private BootstrapApplication getApplication(Component component) {
        if (component.getApplication() instanceof IBootstrapApplication) {
            return (BootstrapApplication) component.getApplication();
        } else {
            throw new WicketRuntimeException("you have to extend BootstrapApplication or implement IBootstrapApplication to use this behavior.");
        }
    }
    
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        IBootstrapSettings settings = getApplication(component).getBootstrapSettings();

        response.renderCSSReference(settings.getCssResourceReference());

        if (settings.useResponsiveCss()) {
            response.renderCSSReference(settings.getResponsiveCssResourceReference());
        }

        response.renderJavaScriptReference(settings.getJqueryUrl(), "jquery");
        response.renderJavaScriptReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true);
    }
}
