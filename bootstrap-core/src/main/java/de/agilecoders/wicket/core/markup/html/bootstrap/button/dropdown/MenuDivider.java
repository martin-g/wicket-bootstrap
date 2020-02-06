package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;

/**
 * A simple divider for menu elements.
 *
 * @author miha
 */
public class MenuDivider extends AbstractLink {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public MenuDivider() {
        super(ButtonList.getButtonMarkupId());
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        tag.append("class", "dropdown-divider", " ");
    }
}
