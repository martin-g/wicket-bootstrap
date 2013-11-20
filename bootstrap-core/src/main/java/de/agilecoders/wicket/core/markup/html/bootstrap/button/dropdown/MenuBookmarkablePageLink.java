package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A bootstrap style {@link BookmarkablePageLink}
 *
 * @param <T>  The type of the model object for this link
 * @author miha
 */
public class MenuBookmarkablePageLink<T> extends BootstrapBookmarkablePageLink<T>  {

    /**
     * Constructor.
     *
     * @param pageClass The class of page to link to
     * @param <P>       type of the page class
     */
    public <P extends Page> MenuBookmarkablePageLink(final Class<P> pageClass) {
        super(ButtonList.getButtonMarkupId(), pageClass, Buttons.Type.Menu);
    }

    /**
     * Constructor.
     *
     * @param pageClass The class of page to link to
     * @param label     button label
     * @param <P>       type of the page class
     */
    public <P extends Page> MenuBookmarkablePageLink(final Class<P> pageClass, final IModel<String> label) {
        super(ButtonList.getButtonMarkupId(), pageClass, Buttons.Type.Menu);

        setLabel(label);
    }

    /**
     * Constructor.
     *
     * @param pageClass  The class of page to link to
     * @param parameters The page parameters
     * @param model      The label
     * @param <P>        type of the page class
     */
    public <P extends Page> MenuBookmarkablePageLink(final Class<P> pageClass, final PageParameters parameters, final IModel<String> model) {
        super(ButtonList.getButtonMarkupId(), pageClass, parameters, Buttons.Type.Menu);

        setLabel(model);
    }

    @Override
    public boolean isActive(final Component button) {
        return getPageClass().equals(button.getPage().getClass());
    }
}
