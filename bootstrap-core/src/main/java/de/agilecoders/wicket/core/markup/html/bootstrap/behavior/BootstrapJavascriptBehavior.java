package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.util.References;

/**
 * #### Description
 *
 * The {@code BootstrapJavascriptBehavior} renders the
 * {@link de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference} to
 * the response.
 */
public class BootstrapJavascriptBehavior extends BootstrapBaseBehavior {
    private static final long serialVersionUID = 1L;

    /**
     * render the {@link de.agilecoders.wicket.core.markup.html.references.BootstrapJavaScriptReference}  to
     * the response.
     *
     * @param settings       the bound {@link IBootstrapSettings}
     * @param headerResponse the current {@link IHeaderResponse}
     */
    @Override
    public void renderHead(IBootstrapSettings settings, IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        final JavaScriptReferenceHeaderItem jsReference = new JavaScriptReferenceHeaderItem(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js");
        jsReference.setDefer(settings.deferJavascript());
        References.renderWithFilter(settings, headerResponse, jsReference);
    }
}
