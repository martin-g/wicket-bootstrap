package de.agilecoders.wicket.markup.html;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.layout.ContainerBorder;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Layout;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
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

    private ContainerBorder border;


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
        border = new ContainerBorder("border");

        add(border);
        add(new BootstrapResourcesBehavior());
    }

    protected BootstrapPage withLayout(Layout layout) {
        border.withLayout(layout);

        return this;
    }

    @Override
    public BootstrapPage add(Component... childs) {
        border.addToBorder(childs);

        return this;
    }

    @Override
    public BootstrapPage addOrReplace(Component... childs) {
        border.addOrReplace(childs);

        return this;
    }

    @Override
    public MarkupContainer remove(Component component) {
        return super.remove(component);
    }

    @Override
    public MarkupContainer remove(String id) {
        return super.remove(id);
    }

    @Override
    public MarkupContainer removeAll() {
        return super.removeAll();
    }

    @Override
    public MarkupContainer replace(Component child) {
        return super.replace(child);
    }

    @Override
    public boolean contains(Component component, boolean recurse) {
        return super.contains(component, recurse);
    }
}
