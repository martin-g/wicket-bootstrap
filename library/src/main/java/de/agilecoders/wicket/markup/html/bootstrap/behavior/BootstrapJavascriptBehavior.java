package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import de.agilecoders.wicket.util.References;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The {@code BootstrapJavascriptBehavior} renders the
 * {@link de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference} and if active
 * the {@link de.agilecoders.wicket.markup.html.references.JqueryPPJavaScriptReference} to
 * the response.
 *
 * @author miha
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

        final JavaScriptReferenceHeaderItem jsReference = JavaScriptHeaderItem.forReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true);
        References.renderWithFilter(settings, headerResponse, jsReference);

        if (settings.useJqueryPP()) {
            JavaScriptReferenceHeaderItem jqueryPP = JavaScriptHeaderItem.forReference(settings.getJqueryPPResourceReference(), new PageParameters(), "jquerypp-js", true);

            References.renderWithFilter(settings, headerResponse, jqueryPP);
        }
    }
}
