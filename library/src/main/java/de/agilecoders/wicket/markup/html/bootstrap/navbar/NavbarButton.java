package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Renders a stable navbar button link which can be cached in a web browser and used at a later time. This
 * button should only be used inside a {@link Navbar} menu.
 *
 * @param <T> type of page class, if any
 * @author miha
 * @version 1.0
 */
public class NavbarButton<T> extends TypedPageButton<T> {
    private static final String COMPONENT_ID = "button";

    /**
     * Constructor.
     *
     * @param pageClass   The class of page to link to
     * @param parameters  The parameters to pass to the new page when the link is clicked
     * @param label       The component's label
     * @param <T>         type of the page class
     */
    public <T extends Page> NavbarButton(final Class<T> pageClass, final PageParameters parameters, final IModel<String> label) {
        super(COMPONENT_ID, pageClass, parameters, ButtonType.Menu);

        setLabel(label);
    }

    /**
     * Constructor.
     *
     * @param pageClass   The class of page to link to
     * @param label       The component's label
     * @param <T>         type of the page class
     */
    public <T extends Page> NavbarButton(final Class<T> pageClass, final IModel<String> label) {
        this(pageClass, new PageParameters(), label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavbarButton<T> setIcon(Icon icon) {
        super.setIcon(icon);

        return this;
    }
}
