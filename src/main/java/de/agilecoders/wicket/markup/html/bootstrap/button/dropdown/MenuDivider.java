package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MenuDivider extends MenuButton {
    public MenuDivider() {
        super();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        getParent().add(new CssClassNameAppender("divider"));
    }

    @Override
    public MenuButton setIcon(Icon icon) {
        return this;
    }
}
