package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconBehavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MenuButton extends Button {
    
    private Icon icon;
    
    public MenuButton() {
        this(Model.<String>of());
    }

    public MenuButton(IModel<String> model) {
        super("menuElement", model);

        this.icon = new Icon("icon", IconBehavior.Type.NULL);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(icon);
    }

    public MenuButton setIcon(Icon icon) {
        icon.setMarkupId("icon");
        this.icon = icon;
        
        return this;
    }
}
