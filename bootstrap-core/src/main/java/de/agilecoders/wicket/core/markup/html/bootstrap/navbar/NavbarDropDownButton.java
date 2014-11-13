package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameRemover;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

import de.agilecoders.wicket.core.util.ListItemCssClassHelper;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Special {@link DropDownButton} for a {@link Navbar}.
 *
 * @author miha
 */
public abstract class NavbarDropDownButton extends DropDownButton {

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
    protected final void addButtonBehavior(final IModel<Buttons.Type> buttonType, final IModel<Buttons.Size> buttonSize) {
        // do nothing, because navbar dropdown button inherits its styles from navbar.
    }

    @Override
    protected WebMarkupContainer newButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
        WebMarkupContainer button = super.newButton(markupId, labelModel, iconTypeModel);
        button.add(new CssClassNameRemover("btn"));
        return button;
    }
}
