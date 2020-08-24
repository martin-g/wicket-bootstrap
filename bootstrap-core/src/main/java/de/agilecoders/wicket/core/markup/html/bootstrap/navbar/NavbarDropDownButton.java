package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.util.ListItemCssClassHelper;

/**
 * Special {@link DropDownButton} for a {@link Navbar}.
 *
 * @author miha
 */
public abstract class NavbarDropDownButton extends DropDownButton {
    private static final long serialVersionUID = 1L;

	/**
     * Construct.
     *
     * @param model the label of this dropdown button
     */
    public NavbarDropDownButton(final IModel<String> model) {
        super(Navbar.componentId(), model);
    }

    /**
     * Construct.
     *
     * @param model the label of this dropdown button
     * @param iconTypeModel the type of the icon
     */
    public NavbarDropDownButton(final IModel<String> model, final IModel<IconType> iconTypeModel) {
        super(Navbar.componentId(), model, iconTypeModel);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.setRenderBodyOnly(true);
        ListItemCssClassHelper.onInitialize(this, "dropdown");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        // add the dropdown css class name to the parent element not button element itself.
        ListItemCssClassHelper.onConfigure(this, "dropdown");
    }

    @Override
    protected final void addButtonBehavior(final ButtonBehavior buttonBehavior) {
        // do nothing, because navbar dropdown button inherits its styles from navbar.
    }

    @Override
    protected WebMarkupContainer newButton(String markupId, IModel<String> labelModel, final IModel<IconType> iconTypeModel) {
        WebMarkupContainer button = super.newButton(markupId, labelModel, iconTypeModel);
        button.add(new CssClassNameAppender(Buttons.Type.NavLink.cssClassName()));
        return button;
    }
}
