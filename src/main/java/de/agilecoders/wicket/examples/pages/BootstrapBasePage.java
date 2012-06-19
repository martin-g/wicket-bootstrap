package de.agilecoders.wicket.examples.pages;

import de.agilecoders.wicket.markup.html.BootstrapPage;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.layout.AbstractLayout;
import de.agilecoders.wicket.markup.html.bootstrap.layout.FixedLayout;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.AbstractRow;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
abstract class BootstrapBasePage extends BootstrapPage {

    private final AbstractLayout layout;
    private Navbar navbar;

    public BootstrapBasePage(PageParameters parameters) {
        super(parameters);

        navbar = newNavbar("navbar");
        add(navbar);

        layout = newLayout("layout");
        add(layout);
    }

    protected Navbar newNavbar(String componentId) {
        Navbar navbar = new Navbar(componentId).fixedTop().brandName(new Model<>("Wicket-Bootstrap"));
        navbar.addButton(Navbar.Position.LEFT, new NavbarButton<ButtonsPage>(ButtonsPage.class, Model.of("Buttons")).setIcon(new Icon("icon", Icon.Type.ThLarge)));
        navbar.addButton(Navbar.Position.LEFT, new NavbarButton<HeadingPage>(HeadingPage.class, Model.of("Headings")).setIcon(new Icon("icon", Icon.Type.AlignJustify)));
        navbar.addButton(Navbar.Position.LEFT, new NavbarButton<FormPage>(FormPage.class, Model.of("Forms")).setIcon(new Icon("icon", Icon.Type.AlignJustify)));
        
        return navbar;
    }

    protected AbstractLayout newLayout(String componentId) {
        return new FixedLayout(componentId);
    }

    protected Navbar getNavbar() {
        return navbar;
    }

    protected void addRow(AbstractRow... components) {
        layout.addRow(components);
    }

    protected AbstractLayout getLayout() {
        return layout;
    }
}
