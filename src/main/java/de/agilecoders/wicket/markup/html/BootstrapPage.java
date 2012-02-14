package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
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

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        // TODO: add all necessary references according to bootstrap settings
        //response.renderJavaScriptReference(BootstrapJavaScriptReference.INSTANCE, new PageParameters(), BootstrapJavaScriptReference.ID, true);
        BootstrapApplication application = (BootstrapApplication) getApplication();
        
        response.renderCSSReference(application.getBootstrapSettings().getCssUri());
        response.renderCSSReference(application.getBootstrapSettings().getCssResponsiveUri());
        
        response.renderJavaScriptReference(application.getBootstrapSettings().getJavaScriptUri(), "bootstrap-js", true);
    }
}
