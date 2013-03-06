/**
 * 
 */
package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.util.Components;

/**
 * A Sub-Menu to be used in {@link DropDownButton} or {@link NavbarDropDownButton}.
 * Just add it to the {@link DropDownButton} like a regular button with {@link DropDownButton#addButton(org.apache.wicket.markup.html.link.AbstractLink)}, etc.
 * You can add menu entries/buttons to this {@link DropDownSubMenu} just like you would add them to {@link DropDownButton}. 
 * 
 * @author heapifyman
 */
public class DropDownSubMenu extends DropDownButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2233352448742071270L;

	/**
	 * Construct.
	 * 
	 * @param model			The label of the sub menu
	 */
	public DropDownSubMenu(final IModel<String> model) {
        super(ButtonList.getButtonMarkupId(), model);
    }

    /**
     * Construct.
     *
     * @param model         The label of the sub menu
     * @param iconTypeModel the type of the icon
     */
    public DropDownSubMenu(final IModel<String> model, final IModel<IconType> iconTypeModel) {
        super(ButtonList.getButtonMarkupId(), model, iconTypeModel);
    }
    
    /**
     * Creates {@link CssClassNameAppender} that adds proper CSS class "dropdown-submenu" to {@link DropDownSubMenu}.
     * 
     * @see de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton#newDropDownCSS()
     */
    @Override
    protected CssClassNameAppender newDropDownCSS() {
    	return new CssClassNameAppender("dropdown-submenu");
    }

    /*
     * (non-Javadoc)
     * @see de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(final ComponentTag tag) {
    	// ensure that dropdown's sub-menu is of type <li>
        if (!Components.hasTagName(tag, "li")) {
            tag.setName("li");
        }

        super.onComponentTag(tag);
    }
    
    /*
     * (non-Javadoc)
     * @see de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton#addButtonBehavior(org.apache.wicket.model.IModel, org.apache.wicket.model.IModel)
     */
    @Override
    protected void addButtonBehavior(IModel<ButtonType> buttonType,
    		IModel<ButtonSize> buttonSize) {
    	// do nothing, because dropdown sub-menu is just another <li>
    }

}
