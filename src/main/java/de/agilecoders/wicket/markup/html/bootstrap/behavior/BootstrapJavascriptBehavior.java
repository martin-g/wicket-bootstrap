package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The {@code BootstrapJavascriptBehavior} renders the
 * {@link de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference} and if active
 * the {@link de.agilecoders.wicket.markup.html.references.JqueryPPJavaScriptReference} to
 * the response.
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapJavascriptBehavior extends BootstrapBaseBehavior {

    /**
     * render the {@link de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference} and if active
     * the {@link de.agilecoders.wicket.markup.html.references.JqueryPPJavaScriptReference} to
     * the response.
     *
     * @param settings       the bound {@link IBootstrapSettings}
     * @param headerResponse the current {@link IHeaderResponse}
     */
    @Override
    public void renderHead(IBootstrapSettings settings, IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        headerResponse.render(JavaScriptHeaderItem.forReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true));

        if (settings.useJqueryPP()) {
            headerResponse.render(JavaScriptHeaderItem.forReference(settings.getJqueryPPResourceReference(), new PageParameters(), "jquerypp-js", true));
        }
    }
}
