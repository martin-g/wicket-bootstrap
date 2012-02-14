package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonCssClassAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DropDownButton extends Panel implements BootstrapButton<DropDownButton> {

    private Component button;
    private Menu menu;
    private IModel<ButtonSize> buttonSize;
    private IModel<ButtonType> buttonType;
    private WebMarkupContainer icon;

    public DropDownButton(String id) {
        this(id, Model.of());
    }

    public DropDownButton(String id, IModel<?> model) {
        super(id, model);

        buttonSize = Model.of(ButtonSize.Medium);
        buttonType = Model.of(ButtonType.Default);
        
        menu = new Menu("menu");
        icon = new WebMarkupContainer("icon");

        add(menu);
        add(icon);
        
        add(new CssClassNameAppender("btn-group"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (button == null) {
            throw new IllegalArgumentException("missing root button");
        }

        button.add(new ButtonCssClassAppender(buttonType, buttonSize));
        icon.add(new ButtonCssClassAppender(buttonType, buttonSize));

        add(button);
    }

    public DropDownButton setButton(Component button) {
        button.setMarkupId("button");
        this.button = button;

        return this;
    }

    public DropDownButton addMenuButton(Component... buttons) {
        menu.addMenuButton(buttons);

        return this;
    }

    @Override
    public DropDownButton setSize(ButtonSize buttonSize) {
        this.buttonSize = Model.of(buttonSize);

        return this;
    }

    public DropDownButton setType(ButtonType buttonType) {
        this.buttonType = Model.of(buttonType);

        return this;
    }
}
