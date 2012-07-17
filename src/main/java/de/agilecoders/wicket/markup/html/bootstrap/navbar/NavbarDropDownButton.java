package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class NavbarDropDownButton extends DropDownButton {

    public NavbarDropDownButton() {
        super("button");
    }

    public NavbarDropDownButton(String id) {
        super(id);
    }

    public NavbarDropDownButton(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        this.setRenderBodyOnly(true);
        this.getParent().add(new CssClassNameAppender("dropdown"));
    }
}
