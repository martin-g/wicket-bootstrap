package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Default {@link AjaxLink} which is styled by bootstrap
 *
 * @author miha
 */
public abstract class BootstrapAjaxLink<T> extends AjaxLink<T> implements IBootstrapButton<BootstrapAjaxLink<T>> {
    private static final long serialVersionUID = 1L;
    private Icon icon;
    private Component label;
    private Component splitter;
    private ButtonBehavior buttonBehavior;
    /** To use the splitter or not (true by default). */
    private boolean useSplitter = true;
    private final Buttons.Type type;
    private final IModel<? extends Serializable> labelModel;
    /**
     * Construct.
     *
     * @param id   the components id
     * @param type the type of the button
     */
    public BootstrapAjaxLink(final String id, final Buttons.Type type) {
        this(id, null, type);
    }

    /**
     * Construct.
     *
     * @param id    The component id
     * @param model The component model
     * @param type  the type of the button
     */
    public BootstrapAjaxLink(String id, IModel<T> model, Buttons.Type type) {
        this(id, model, type, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id    The component id
     * @param model The component model
     * @param type  the type of the button
     * @param labelModel The model for the link's label
     */
    public <L extends Serializable> BootstrapAjaxLink(String id, IModel<T> model, Buttons.Type type, IModel<L> labelModel) {
        super(id, model);
        this.type = type;
        this.labelModel = labelModel;
        add(getButtonBehavior());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getIcon(), getSplitter(), getLinkLabel());
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

    private Component getLinkLabel() {
        if (label == null) {
            label = newLabel("label", labelModel);
        }
        return label;
    }

    /**
     * Creates a new icon component
     *
     * @param markupId the component id of the icon
     * @return new icon component
     */
    protected Icon newIcon(final String markupId) {
        return new Icon(markupId, (IconType) null);
    }

    /**
     * Creates a new label component
     *
     * @param markupId the component id of the label
     * @return new label component
     */
    protected <L extends Serializable> Component newLabel(final String markupId, IModel<L> model) {
        return new Label(markupId, model)
                .setRenderBodyOnly(true);
    }



   /**
    * Creates a new splitter component. The splitter is visible only
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
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (useSplitter) {
            getSplitter().setVisible(getIcon().hasIconType() && StringUtils.isNotEmpty(getLinkLabel().getDefaultModelObjectAsString()));
        }
    }

    /**
     * Sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public <L extends Serializable> BootstrapAjaxLink<T> setLabel(IModel<L> label) {
        getLinkLabel().setDefaultModel(label);
        return this;
    }

    /**
     * Sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapAjaxLink<T> setIconType(IconType iconType) {
        getIcon().setType(iconType);
        return this;
    }

    /**
     * Sets the size.
     */
    @Override
    public BootstrapAjaxLink<T> setSize(Buttons.Size size) {
        getButtonBehavior().setSize(size);
        return this;
    }

    /**
     * Sets the type.
     */
    @Override
    public BootstrapAjaxLink<T> setType(Buttons.Type type) {
        getButtonBehavior().setType(type);
        return this;
    }

    /**
     * @param value whether to use splitter between the icon and the label or not
     * @return this instance for chaining
     */
    public BootstrapAjaxLink<T> useSplitter(boolean value) {
        this.useSplitter = value;
        return this;
    }

    @Override
    protected void detachModel() {
        super.detachModel();
        labelModel.detach();
    }
}
