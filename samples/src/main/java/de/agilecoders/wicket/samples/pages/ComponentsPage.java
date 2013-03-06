package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownSubMenu;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.button.DropDownAutoOpen;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/components")
public class ComponentsPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ComponentsPage(PageParameters parameters) {
        super(parameters);
        
        // add example for dropdown button with sub-menu
        add(newDropDownSubMenuExample());
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
    
    /**
     * 
     * @return
     */
    private Component newDropDownSubMenuExample() {
    	return new DropDownButton("dropDownSubMenuExample", Model.of("Addons"))
    		.addButton(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh))
    		.addButton(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time))
	        .addButton(new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.book))
	        .addButton(new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.alignjustify))
	        .addButton(new MenuDivider())
	        .addButton(new DropDownSubMenu(Model.of("Sub-Menu Level 1")).setIconType(IconType.arrowright)
	        	.addButton(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh))
	        	.addButton(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time))
	        	.addButton(new MenuDivider())
	        	.addButton(new DropDownSubMenu(Model.of("Sub-Menu Level 2"))
	        		.addButton(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh))
	        		.addButton(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time))))
	        .setIconType(IconType.thlarge).add(new DropDownAutoOpen());
    }
}
