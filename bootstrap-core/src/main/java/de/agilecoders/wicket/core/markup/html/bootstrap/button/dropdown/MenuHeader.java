package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

/**
 * A simple header for {@link DropDownButton} and {@link SplitButton} submenus.
 *
 * @author miha
 */
public class MenuHeader extends AbstractLink {

    /**
     * Construct.
     *
     * @param label Header label
     */
    public MenuHeader(final IModel<String> label) {
        super(ButtonList.getButtonMarkupId());

        setBody(label);
    }
    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        tag.setName("div");
        tag.getAttributes().clear();
        tag.append("class", "dropdown-header", " ");
    }
}
