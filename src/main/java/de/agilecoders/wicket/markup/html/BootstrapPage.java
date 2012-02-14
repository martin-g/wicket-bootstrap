package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Base class for bootstrap HTML pages. This subclass of WebPage simply adds all needed bootstrap
 * framework resource references.
 * <p/>
 *
 * @author miha
 * @version 1.0
 * @see WebPage
 */
public abstract class BootstrapPage extends WebPage {

    /**
     * Constructor.
     *
     * @see org.apache.wicket.markup.html.WebPage#WebPage()
     */
    protected BootstrapPage() {
        commonInit();
    }

    /**
     * Constructor.
     *
     * @param model @see Page#Page(IModel)
     * @see WebPage#WebPage(org.apache.wicket.model.IModel)
     */
    protected BootstrapPage(IModel<?> model) {
        super(model);

        commonInit();
    }

    /**
     * Constructor.
     *
     * @param parameters Wrapped query string parameters.
     * @see WebPage#WebPage(org.apache.wicket.request.mapper.parameter.PageParameters)
     */
    protected BootstrapPage(PageParameters parameters) {
        super(parameters);

        commonInit();
    }

    /**
     * Common code executed by constructors.
     */
    private void commonInit() {
        // so far a noop
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (getApplication() instanceof BootstrapApplication) {
            IBootstrapSettings settings = ((BootstrapApplication) getApplication()).getBootstrapSettings();

            response.renderCSSReference(settings.getCssResourceReference());

            if (settings.useResponsiveCss()) {
                response.renderCSSReference(settings.getResponsiveCssResourceReference());
            }

            response.renderJavaScriptReference(settings.getJqueryUrl(), "jquery");
            response.renderJavaScriptReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true);
        } else {
            throw new WicketRuntimeException("you have to extend BootstrapApplication to use this page.");
        }
    }
}
