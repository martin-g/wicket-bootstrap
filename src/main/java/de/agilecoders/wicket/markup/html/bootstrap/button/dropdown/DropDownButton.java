package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
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

    private Component label;
    private Menu menu;
    private IModel<ButtonSize> buttonSize;
    private IModel<ButtonType> buttonType;

    public DropDownButton(String id) {
        this(id, Model.of());
    }

    public DropDownButton(String id, IModel<?> model) {
        super(id, model);

        buttonSize = Model.of(ButtonSize.Medium);
        buttonType = Model.of(ButtonType.Default);

        label = new Label("label");
        label.setRenderBodyOnly(true);
        menu = new Menu("menu");
        add(menu, label);

        add(new CssClassNameAppender("btn-group"));
        add(new BootstrapResourcesBehavior());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnLoadHeaderItem.forScript("$('.dropdown-toggle').dropdown()"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        //label.add(new ButtonCssClassAppender(buttonType, buttonSize));
    }

    public DropDownButton setButtonLabel(IModel<String> label) {
        this.label.setDefaultModel(label);

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
