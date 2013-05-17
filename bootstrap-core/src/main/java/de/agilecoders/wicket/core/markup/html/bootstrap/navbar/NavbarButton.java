package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Renders a stable navbar button link which can be cached in a web browser and used at a later time. This
 * button should only be used inside a {@link Navbar} menu.
 *
 * @param <T> type of page class, if any
 * @author miha
 */
public class NavbarButton<T> extends BootstrapBookmarkablePageLink<T> {

    /**
     * Constructor.
     *
     * @param pageClass  The class of page to link to
     * @param parameters The parameters to pass to the new page when the link is clicked
     * @param label      The component's label
     * @param <T>        type of the page class
     */
    public <T extends Page> NavbarButton(final Class<T> pageClass, final PageParameters parameters, final IModel<String> label) {
        super(Navbar.componentId(), pageClass, parameters, Buttons.Type.Menu);

        setLabel(label);
    }

    /**
     * Constructor.
     *
     * @param pageClass The class of page to link to
     * @param label     The component's label
     * @param <T>       type of the page class
     */
    public <T extends Page> NavbarButton(final Class<T> pageClass, final IModel<String> label) {
        this(pageClass, new PageParameters(), label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavbarButton<T> setIconType(final IconType icon) {
        super.setIconType(icon);

        return this;
    }


    @Override
    protected void onComponentTag(final ComponentTag tag) {
        if (!Components.hasTagName(tag, "a", "button", "input")) {
            tag.setName("a");
        }

        super.onComponentTag(tag);
    }
}
