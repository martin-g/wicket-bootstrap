package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 * @version 1.0
 */
public class DropDownButton extends AbstractLink implements Invertible {

    private final IModel<ButtonSize> buttonSize = Model.of(ButtonSize.Medium);
    private final IModel<ButtonType> buttonType = Model.of(ButtonType.Menu);
    private final IModel<Boolean> dropUp = Model.of(false);
    private final ButtonList buttonListView;
    private final IModel<IconType> iconTypeModel;
    private final Component baseButton;

    private Icon icon;

    /**
     * Construct.
     *
     * @param id    The markup id
     * @param model The label of the main button
     */
    public DropDownButton(final String id, final IModel<String> model) {
        this(id, model, Model.of(IconType.NULL));
    }

    /**
     * Construct.
     *
     * @param markupId      The markup id
     * @param model         The label of the main button
     * @param iconTypeModel the type of the icon
     */
    public DropDownButton(final String markupId, final IModel<String> model, final IModel<IconType> iconTypeModel) {
        super(markupId, model);

        this.iconTypeModel = iconTypeModel;

        add(baseButton = createButton("btn", model, iconTypeModel));
        add(new CssClassNameAppender("btn-group"));
        add(new AssertTagNameBehavior("div"));
        add(new BootstrapResourcesBehavior());
        add(buttonListView = newButtonList("buttons"));

        addButtonBehavior(buttonType, buttonSize);
    }

    /**
     * sets the icon of the base button element
     *
     * @param iconType The {@link IconType} of the icon
     * @return this element instance
     */
    public final DropDownButton setIcon(IconType iconType) {
        iconTypeModel.setObject(iconType);
        return this;
    }

    /**
     * creates a new button instance
     *
     * @param markupId      The component's id
     * @param labelModel    The label text as model
     * @param iconTypeModel The icon type as model
     * @return a new button component
     */
    protected Component createButton(final String markupId, final IModel<String> labelModel, final IModel<IconType> iconTypeModel) {
        final WebMarkupContainer baseButton = new WebMarkupContainer(markupId);

        baseButton.setOutputMarkupId(true);
        baseButton.add(createButtonLabel("label", labelModel));
        baseButton.add(icon = createButtonIcon("icon", iconTypeModel));

        return baseButton;
    }

    /**
     * creates a new icon component with given {@link IconType}.
     *
     * @param markupId   The component' id
     * @param labelModel The label text as model
     * @return new label component
     */
    protected Component createButtonLabel(final String markupId, final IModel<?> labelModel) {
        final Label label = new Label(markupId, labelModel);
        label.setRenderBodyOnly(true);

        return label;
    }

    /**
     * creates a new icon component with given {@link IconType}.
     *
     * @param markupId      The component' id
     * @param iconTypeModel The icon type as model
     * @return new icon component
     */
    protected Icon createButtonIcon(final String markupId, final IModel<IconType> iconTypeModel) {
        return new Icon(markupId, iconTypeModel).invert();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript("$('.dropdown-toggle').dropdown()"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (dropUp.getObject()) {
            add(new CssClassNameAppender("dropup"));
        }
    }

    protected void addButtonBehavior(final IModel<ButtonType> buttonType, final IModel<ButtonSize> buttonSize) {
        baseButton.add(new ButtonBehavior(buttonType, buttonSize));
    }

    public DropDownButton addButton(AbstractLink button) {
        return addButtons(button);
    }

    public DropDownButton addButtons(AbstractLink... buttons) {
        buttonListView.addButtons(buttons);

        return this;
    }

    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId);
        buttonList.setRenderBodyOnly(true);

        return buttonList;
    }

    public DropDownButton setDropUp(boolean dropUp) {
        this.dropUp.setObject(dropUp);

        return this;
    }

    public DropDownButton setSize(ButtonSize buttonSize) {
        this.buttonSize.setObject(buttonSize);

        return this;
    }

    public DropDownButton setType(ButtonType buttonType) {
        this.buttonType.setObject(buttonType);

        return this;
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }

    @Override
    public void setInverted(boolean inverted) {
        if (icon != null) {
            icon.setInverted(inverted);
        }

        if (baseButton instanceof Invertible) {
            ((Invertible) baseButton).setInverted(inverted);
        }
    }
}
