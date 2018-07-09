package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;

import de.agilecoders.wicket.core.util.ListItemCssClassHelper;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;

/**
 * A simple divider for menu elements.
 *
 * @author miha
 */
public class MenuDivider extends AbstractLink {

    /**
     * Construct.
     */
    public MenuDivider() {
        super(ButtonList.getButtonMarkupId());

        setBody(Model.of("&nbsp;"));
        setEscapeModelStrings(false);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ListItemCssClassHelper.onInitialize(this, "dropdown-divider");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        ListItemCssClassHelper.onConfigure(this, "dropdown-divider");
    }
}
