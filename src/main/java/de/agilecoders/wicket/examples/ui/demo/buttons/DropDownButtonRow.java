package de.agilecoders.wicket.examples.ui.demo.buttons;

import de.agilecoders.wicket.examples.pages.ButtonsPage;
import de.agilecoders.wicket.examples.pages.HeadingPage;
import de.agilecoders.wicket.examples.pages.HomePage;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.DefaultButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuDividerButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.Row;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DropDownButtonRow extends Row {
    public DropDownButtonRow() {
        super();

        DropDownButton dropDownButton = new DropDownButton("dropdown");
        dropDownButton.setButton(new DefaultButton("button"));
        dropDownButton.addMenuButton(new MenuButton().setIcon(new Icon("icon", Icon.Type.Film)),
                                     new MenuButton().setIcon(new Icon("icon", Icon.Type.Heart)),
                                     new MenuDividerButton(),
                                     new MenuPageButton<HomePage>(HomePage.class, Model.of("Homepage")).setIcon(new Icon("icon", Icon.Type.Home)));
        add(dropDownButton);

        DropDownButton dropDownButton2 = new DropDownButton("dropdown2").setType(ButtonType.Warning);
        dropDownButton2.setButton(new DefaultButton("button"));
        dropDownButton2.addMenuButton(new MenuButton().setIcon(new Icon("icon", Icon.Type.File)), new MenuButton(), new MenuDividerButton(), new MenuPageButton<HomePage>(HomePage.class, Model.of("Homepage")));
        add(dropDownButton2);

        DropDownButton dropDownButton3 = new DropDownButton("dropdown3").setType(ButtonType.Success).setSize(ButtonSize.Large);
        dropDownButton3.setButton(new DefaultButton("button"));
        dropDownButton3.addMenuButton(new MenuButton(), new MenuButton(), new MenuDividerButton(), new MenuPageButton<HomePage>(HomePage.class, Model.of("Homepage")));
        add(dropDownButton3);
        
        add(new TypedPageButton<HomePage>("pageButton1", HomePage.class, ButtonType.Primary).setLabel(new Model<>("Homepage")).setIcon(new Icon("icon", Icon.Type.Home)));
        add(new TypedPageButton<ButtonsPage>("pageButton2", ButtonsPage.class, ButtonType.Danger).setLabel(new Model<>("Buttons")).setIcon(new Icon("icon", Icon.Type.ThLarge)).setSize(ButtonSize.Large));
        add(new TypedPageButton<HeadingPage>("pageButton3", HeadingPage.class, ButtonType.Warning).setLabel(new Model<>("Heading")).setIcon(new Icon("icon", Icon.Type.AlignJustify)));
    }
}
