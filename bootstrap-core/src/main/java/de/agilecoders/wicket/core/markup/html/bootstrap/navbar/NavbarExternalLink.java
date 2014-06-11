package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

/**
 * external link component that can be used in a navbar component.
 *
 * @author miha
 */
public class NavbarExternalLink extends BootstrapExternalLink {

    /**
     * Construct.
     *
     * @param href the link destination
     */
    public NavbarExternalLink(IModel<String> href) {
        super(Navbar.componentId(), href);
    }

    /**
     * Construct.
     *
     * @param href the link destination
     * @param type the button type
     */
    public NavbarExternalLink(IModel<String> href, Buttons.Type type) {
        super(Navbar.componentId(), href, type);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        if (!"a".equalsIgnoreCase(tag.getName())) {
            tag.setName("a");
        }

        super.onComponentTag(tag);
    }
}
