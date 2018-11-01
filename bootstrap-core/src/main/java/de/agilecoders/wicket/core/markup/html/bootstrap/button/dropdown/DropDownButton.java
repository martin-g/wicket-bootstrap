package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.AlignmentBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.jquery.JQuery;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.util.List;

import static de.agilecoders.wicket.core.markup.html.bootstrap.button.DropDownJqueryFunction.dropdown;
import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 */
public abstract class DropDownButton extends AbstractLink implements Activatable
{

    private final IModel<Buttons.Size> buttonSize = Model.of(Buttons.Size.Medium);
    private final IModel<Buttons.Type> buttonType = Model.of(Buttons.Type.Secondary);
    private final ButtonList buttonListView;
    private final WebMarkupContainer baseButton;
    private final Icon icon;
   
    private final IModel<DropDownAlignmentBehavior.Alignment> alignment = Model
			.of(DropDownAlignmentBehavior.Alignment.NONE);
	private final IModel<DropDownVariationBehavior.Variation> variation = Model
			.of(DropDownVariationBehavior.Variation.DROPDOWN);

    /**
     * Construct.
     *
     * @param id    The markup id
     * @param model The label of the main button
     */
    public DropDownButton(final String id, final IModel<String> model) {
        this(id, model, Model.of((IconType) null));
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

        add(baseButton = newButton("btn", model, iconTypeModel));
        add(new DropDownVariationBehavior(variation));
        
        WebMarkupContainer dropdownMenu = new WebMarkupContainer("dropdown-menu");
        dropdownMenu.add(new AttributeModifier("aria-labelledby", new IModel<String>() {

			@Override
			public String getObject() {
				return baseButton.getMarkupId(true);
			}
		}));
        add(dropdownMenu);
        dropdownMenu.add(buttonListView = newButtonList("buttons"));
        dropdownMenu.add(new DropDownAlignmentBehavior(alignment));

        this.icon = newButtonIcon("icon", iconTypeModel);

        BootstrapResourcesBehavior.addTo(this);

        addIconToBaseButton(icon);
        addButtonBehavior(buttonType, buttonSize);
    }

    /**
     * @return base css class name of button container element
     */
    protected String createCssClassName() {
        return "btn-group";
    }

    /**
     * adds an icon to the base button
     *
     * @param icon The icon to add
     */
    protected void addIconToBaseButton(final Icon icon) {
        baseButton.add(icon);
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
    protected String newInitializerScript() {
        JQuery jQuery = $(baseButton);

        return jQuery.chain(dropdown()).get();
    }

    /**
     * appends a toggle menu script to a given {@link IPartialPageRequestHandler}.
     *
     * @param target the current target
     */
    public final void appendToggleMenuScript(final IPartialPageRequestHandler target) {
        target.appendJavaScript($(this, ".dropdown-toggle").chain(dropdown("toggle")).get());
    }

    /**
     * sets the icon of the base button element
     *
     * @param iconType The {@link IconType} of the icon
     * @return this element instance
     */
    public final DropDownButton setIconType(final IconType iconType) {
        icon.setType(iconType);
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
    protected WebMarkupContainer newButton(final String markupId, final IModel<String> labelModel, final IModel<IconType> iconTypeModel) {
        final WebMarkupContainer baseButton = new WebMarkupContainer(markupId);

        baseButton.setOutputMarkupId(true);
        baseButton.add(newButtonLabel("label", wrap(labelModel)));

        return baseButton;
    }

    /**
     * creates a new icon component with given {@link IconType}.
     *
     * @param markupId   The component' id
     * @param labelModel The label text as model
     * @return new label component
     */
    protected Component newButtonLabel(final String markupId, final IModel<?> labelModel) {
        final Label label = new Label(markupId, wrap(labelModel));
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
    protected Icon newButtonIcon(final String markupId, final IModel<IconType> iconTypeModel) {
        return new Icon(markupId, iconTypeModel);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        String script = newInitializerScript();
        if (!Strings.isEmpty(script)) {
            response.render(OnDomReadyHeaderItem.forScript(script));
        }
    }

    /**
     * adds the button behavior to the base button of this dropdown component
     *
     * @param buttonType mandatory parameter
     * @param buttonSize mandatory parameter
     */
    protected void addButtonBehavior(final IModel<Buttons.Type> buttonType, final IModel<Buttons.Size> buttonSize) {
        baseButton.add(new ButtonBehavior(buttonType, buttonSize));
    }

    /**
     * creates a list of sub menu buttons which will be shown if sub menu will be opened.
     *
     * @param buttonMarkupId the markup id that all sub menu buttons must be use.
     * @return list of sub menu buttons
     */
    protected abstract List<AbstractLink> newSubMenuButtons(final String buttonMarkupId);

    /**
     * creates a new {@link ButtonList} that contains all buttons from {@link #newSubMenuButtons(String)}
     *
     * @param markupId the markup id of {@link ButtonList}
     * @return new {@link ButtonList} instance
     */
    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId, newSubMenuButtons(ButtonList.getButtonMarkupId()));
		buttonList.setRenderBodyOnly(true);
		buttonList.setOutputMarkupId(false);

        return buttonList;
    }

    /**
     * sets the size of the button
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public DropDownButton setSize(final Buttons.Size size) {
        this.buttonSize.setObject(size);
        return this;
    }

    /**
     * sets the type of the button
     *
     * @param type mandatory parameter
     * @return this instance for chaining
     */
    public DropDownButton setType(final Buttons.Type type) {
        this.buttonType.setObject(type);
        return this;
    }

    /**
     * sets the dropdown menu alignment
     *
     * @param alignment The alignment of the button
     * @return this instance for chaining
     */
    public DropDownButton setAlignment(final DropDownAlignmentBehavior.Alignment alignment) {
        this.alignment.setObject(alignment);
        return this;
    }
    
    /**
	 * Sets the dropdown menu "drop" variation
	 *
	 * @param variation
	 *            The "drop" variant of the button
	 * @return this instance for chaining
	 */
	public B4DropDownButton setVariation(final DropDownVariationBehavior.Variation variation) {
		this.variation.setObject(variation);
		return this;
	}

    @Override
    protected final IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        if (!Components.hasTagName(tag, "div", "li")) {
            tag.setName("div");
        }

        super.onComponentTag(tag);

        Attributes.addClass(tag, createCssClassName());
    }

    @Override
    public boolean isActive(final Component item) {
        return buttonListView.hasActiveButton(item);
    }

}
