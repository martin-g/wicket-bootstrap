package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static de.agilecoders.wicket.markup.html.bootstrap.button.DropDownJqueryFunction.dropdown;
import static de.agilecoders.wicket.util.JQuery.$;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 */
public class DropDownButton extends AbstractLink implements Invertible<DropDownButton>, Activatable {

    private final IModel<ButtonSize> buttonSize = Model.of(ButtonSize.Medium);
    private final IModel<ButtonType> buttonType = Model.of(ButtonType.Default);
    private final IModel<Boolean> dropUp = Model.of(false);
    private final ButtonList buttonListView;
    private final IModel<IconType> iconTypeModel;
    private final WebMarkupContainer baseButton;
    private final String script;
    private final Icon icon;

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
        this.script = createInitializerScript();

        add(baseButton = createButton("btn", model, iconTypeModel));
        add(buttonListView = newButtonList("buttons"));

        baseButton.add(icon = createButtonIcon("icon", iconTypeModel));

        add(new BootstrapResourcesBehavior());
        add(new CssClassNameAppender("dropdown"));

        addButtonBehavior(buttonType, buttonSize);
    }

    /**
     * adds a {@link Behavior} to the base button
     *
     * @param behavior to add
     * @return this instance for chaining
     */
    public final DropDownButton addToButton(final Behavior behavior) {
        baseButton.add(behavior);
        return this;
    }

    /**
     * @return new initializer script
     */
    protected String createInitializerScript() {
        return $(this, ".dropdown-toggle").chain(dropdown()).get();
    }

    /**
     * appends a toggle menu script to a given {@link AjaxRequestTarget}.
     *
     * @param target the current target
     */
    public final void appendToggleMenuScript(final AjaxRequestTarget target) {
        target.appendJavaScript($(this, ".dropdown-toggle").chain(dropdown("toggle")).get());
    }

    /**
     * sets the icon of the base button element
     *
     * @param iconType The {@link IconType} of the icon
     * @return this element instance
     */
    public final DropDownButton setIconType(final IconType iconType) {
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
    protected WebMarkupContainer createButton(final String markupId, final IModel<String> labelModel, final IModel<IconType> iconTypeModel) {
        final WebMarkupContainer baseButton = new WebMarkupContainer(markupId);

        baseButton.setOutputMarkupId(true);
        baseButton.add(createButtonLabel("label", labelModel));

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
        return new Icon(markupId, iconTypeModel);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript(script));
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

    public DropDownButton addButton(final AbstractLink button) {
        return addButtons(button);
    }

    public DropDownButton addButtons(final AbstractLink... buttons) {
        buttonListView.addButtons(buttons);

        return this;
    }

    public DropDownButton addButtons(final List<? extends AbstractLink> buttons) {
        for (AbstractLink link : buttons) {
            buttonListView.addButton(link);
        }
        return this;
    }

    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId);
        buttonList.setRenderBodyOnly(true);

        return buttonList;
    }

    public DropDownButton setDropUp(final boolean dropUp) {
        this.dropUp.setObject(dropUp);

        return this;
    }

    public DropDownButton setSize(final ButtonSize buttonSize) {
        this.buttonSize.setObject(buttonSize);

        return this;
    }

    public DropDownButton setType(final ButtonType buttonType) {
        this.buttonType.setObject(buttonType);

        return this;
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        if (!Components.hasTagName(tag, "div", "li")) {
            tag.setName("div");
        }

        super.onComponentTag(tag);
    }

    @Override
    public DropDownButton setInverted(final boolean inverted) {
        icon.setInverted(inverted);
        return this;
    }

    @Override
    public boolean isActive(Component item) {
        final Class<? extends Page> pageClass = item.getPage().getPageClass();

        for (AbstractLink link : buttonListView.getList()) {
            if (link instanceof Activatable) {
                return ((Activatable) link).isActive(item);
            } else if (link instanceof BookmarkablePageLink) {
                if (((BookmarkablePageLink) link).getPageClass().equals(pageClass)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the base button instance
     */
    public final Component getBaseButton() {
        return baseButton;
    }
}
