package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A bootstrap style {@link BookmarkablePageLink}
 *
 * @author miha
 */
public class MenuBookmarkablePageLink<T> extends TypedBookmarkablePageLink<T> {

    /**
     * Constructor.
     *
     * @param pageClass The class of page to link to
     * @param <T>       type of the page class
     */
    public <T extends Page> MenuBookmarkablePageLink(final Class<T> pageClass) {
        super(ButtonList.getButtonMarkupId(), pageClass, ButtonType.Menu);
    }

    /**
     * Constructor.
     *
     * @param pageClass The class of page to link to
     * @param label     button label
     * @param <T>       type of the page class
     */
    public <T extends Page> MenuBookmarkablePageLink(final Class<T> pageClass, final IModel<String> label) {
        super(ButtonList.getButtonMarkupId(), pageClass, ButtonType.Menu);

        setLabel(label);
        setIconType(IconType.NULL);
    }

    /**
     * Constructor.
     *
     * @param pageClass  The class of page to link to
     * @param parameters The page parameters
     * @param model      The label
     * @param <T>        type of the page class
     */
    public <T extends Page> MenuBookmarkablePageLink(final Class<T> pageClass, final PageParameters parameters, final IModel<String> model) {
        super(ButtonList.getButtonMarkupId(), pageClass, parameters, ButtonType.Menu);

        setLabel(model);
    }

    @Override
    public boolean isActive(final Component button) {
        return getPageClass().equals(button.getPage().getClass());
    }
}
