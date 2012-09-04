package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.MarkupException;
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
        super(ButtonList.getButtonMarkupId(), model);

        this.setLabel(model);
        this.icon = new Icon("icon", IconType.NULL);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(icon);
    }

    public MenuButton setIcon(Icon icon) {
        if (!"icon".equals(icon.getId())) {
            throw new MarkupException("icon must use 'icon' as markup id. Please use 'new Icon(IconType)' to create your Icon instance.");
        }

        this.icon = icon;

        return this;
    }
}
