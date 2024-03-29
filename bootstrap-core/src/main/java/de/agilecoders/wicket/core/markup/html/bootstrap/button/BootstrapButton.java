package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * A bootstrap styled {@link Button}
 *
 * @author miha
 */
public class BootstrapButton extends Button implements IBootstrapButton<BootstrapButton> {
    private static final long serialVersionUID = 1L;
    private final Buttons.Type type;
    /** The icon of the button. */
    private Icon icon;
    /** The label of the button. */
    private Component label;
    /** The splitter (space between the icon and the label. */
    private Component splitter;
    /** The button behavior. */
    private ButtonBehavior buttonBehavior;
    /** To use the splitter or not (true by default). */
    private boolean useSplitter = true;

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param type        The type of the button
     */
    public BootstrapButton(final String componentId, final Buttons.Type type) {
        this(componentId, null, type);
    }

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param model       The label model
     * @param type        The type of the button
     */
    public BootstrapButton(final String componentId, final IModel<String> model, final Buttons.Type type) {
        super(componentId, model);

        this.type = type;
        add(getButtonBehavior());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getIcon(), getSplitter(), getButtonLabel());
    }

    private ButtonBehavior getButtonBehavior() {
        if (buttonBehavior == null) {
            buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium);
        }
        return buttonBehavior;
    }

    private Icon getIcon() {
        if (icon == null) {
            icon = newIcon("icon");
        }
        return icon;
    }

    private Component getSplitter() {
        if (splitter == null) {
            splitter = newSplitter("splitter");
        }
        return splitter;
    }

    private Component getButtonLabel() {
        if (label == null) {
            label = newLabel("label", getModel());
        }
        return label;
    }

    /**
     * creates a new icon component
     *
     * @param markupId the component id of the icon
     * @return new icon component
     */
    protected Icon newIcon(final String markupId) {
        return new Icon(markupId, (IconType) null);
    }

    /**
     * creates a new label component
     *
     * @param markupId the component id of the label
     * @return new label component
     */
    protected Component newLabel(final String markupId, IModel<String> model) {
        return new Label(markupId, model)
                .setRenderBodyOnly(true);
    }

    /**
     * creates a new splitter component. The splitter is visible only
     * if icon is visible or if useSplitter is true.
     *
     * @param markupId the component id of the splitter
     * @return new splitter component
     */
    protected Component newSplitter(final String markupId) {
        return new WebMarkupContainer(markupId)
                .setRenderBodyOnly(true)
                .setEscapeModelStrings(false)
                .setVisible(false);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (useSplitter) {
            getSplitter().setVisible(getIcon().hasIconType() && StringUtils.isNotEmpty(getModelObject()));
        }
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    @Override
    public BootstrapButton setLabel(IModel<String> label) {
        getButtonLabel().setDefaultModel(label);
        //the label is also store in the button's model
        setModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return reference to the current instance
     */
    public BootstrapButton setIconType(final IconType iconType) {
        getIcon().setType(iconType);
        return this;
    }

    @Override
    public BootstrapButton setSize(Buttons.Size size) {
        getButtonBehavior().setSize(size);
        return this;
    }

    @Override
    public BootstrapButton setType(Buttons.Type type) {
        getButtonBehavior().setType(type);
        return this;
    }

    /**
     * @param value whether to use splitter between the icon and the label or not
     * @return this instance for chaining
     */
    public BootstrapButton useSplitter(boolean value) {
        this.useSplitter = value;
        return this;
    }
}
