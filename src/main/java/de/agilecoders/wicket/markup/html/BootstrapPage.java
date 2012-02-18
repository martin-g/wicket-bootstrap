package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
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
        add(new BootstrapResourcesBehavior());
    }

}
