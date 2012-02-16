package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import de.agilecoders.wicket.protocol.http.IBootstrapApplication;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapHead {

    private final BootstrapApplication application;

    public BootstrapHead(Application application) {
        if (application instanceof IBootstrapApplication) {
            this.application = (BootstrapApplication) application;
        } else {
            throw new WicketRuntimeException("you have to extend BootstrapApplication or implement IBootstrapApplication to use this page.");
        }
    }

    public void renderHead(IHeaderResponse response) {
        IBootstrapSettings settings = application.getBootstrapSettings();

        response.renderCSSReference(settings.getCssResourceReference());

        if (settings.useResponsiveCss()) {
            response.renderCSSReference(settings.getResponsiveCssResourceReference());
        }

        response.renderJavaScriptReference(settings.getJqueryUrl(), "jquery");
        response.renderJavaScriptReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true);
    }
}
