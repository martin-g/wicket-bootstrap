package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResourcesBehavior extends BootstrapJavascriptBehavior {

    @Override
    public void renderHead(IBootstrapSettings settings, IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        headerResponse.render(CssHeaderItem.forReference(settings.getCssResourceReference()));

        if (settings.useResponsiveCss()) {
            headerResponse.render(CssHeaderItem.forReference(settings.getResponsiveCssResourceReference()));
        }

        headerResponse.render(JavaScriptHeaderItem.forReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true));
    }

}
