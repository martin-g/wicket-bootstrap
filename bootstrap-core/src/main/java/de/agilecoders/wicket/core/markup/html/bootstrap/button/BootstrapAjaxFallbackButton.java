package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * a bootstrap styled {@link AjaxFallbackButton}
 *
 * @author miha
 */
public abstract class BootstrapAjaxFallbackButton extends AjaxFallbackButton implements IBootstrapButton<BootstrapAjaxFallbackButton> {
    private static final long serialVersionUID = 1L;
    private Icon icon;
    private Component label;
    private Component splitter;
    private ButtonBehavior buttonBehavior;
    /** To use the splitter or not (true by default). */
    private boolean useSplitter = true;
    private final Buttons.Type type;

    /**
     * Construct.
     *
     * @param id   the components id
     * @param form The assigned form
     * @param type the button type
     */
    public BootstrapAjaxFallbackButton(String id, Form<?> form, Buttons.Type type) {
        this(id, Model.<String>of(), form, type);
    }

    /**
     * Construct.
     *
     * @param id    the components id
     * @param model The label
     * @param form  The assigned form
     * @param type  the button type
     */
    public BootstrapAjaxFallbackButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form);
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
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
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
    protected <L extends Serializable> Component newLabel(final String markupId, IModel<L> model) {
        return new Label(markupId, model)
                .setRenderBodyOnly(true);
    }

    /**
     * creates a new splitter component. The splitter is visible only
     * if icon is visible and useSplitter is true.
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

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    @Override
    public BootstrapAjaxFallbackButton setLabel(IModel<String> label) {
        getButtonLabel().setDefaultModel(label);
        //the label is also in the button model
        setModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapAjaxFallbackButton setIconType(IconType iconType) {
        getIcon().setType(iconType);

        return this;
    }

    /**
     * sets the size of the button
     *
     * @param size The button size
     * @return this instance for chaining
     */
    @Override
	public BootstrapAjaxFallbackButton setSize(Buttons.Size size) {
        getButtonBehavior().setSize(size);
        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param type The type of the button
     * @return this instance for chaining
     */
    @Override
	public BootstrapAjaxFallbackButton setType(Buttons.Type type) {
        getButtonBehavior().setType(type);
        return this;
    }

    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapAjaxFallbackButton setBlock(boolean block) {
        getButtonBehavior().setBlock(block);
        return this;
    }

    /**
     * @param value whether to use splitter between the icon and the label or not
     * @return this instance for chaining
     */
    public BootstrapAjaxFallbackButton useSplitter(boolean value) {
        this.useSplitter = value;
        return this;
    }
}
