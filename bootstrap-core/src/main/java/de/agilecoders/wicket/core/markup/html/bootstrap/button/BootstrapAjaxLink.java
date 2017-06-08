package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * Default {@link AjaxLink} which is styled by bootstrap
 *
 * @author miha
 */
public abstract class BootstrapAjaxLink<T> extends AjaxLink<T> implements IBootstrapButton<BootstrapAjaxLink<T>> {

    private final Icon icon;
    private final Component label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

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

        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));
        add(icon = newIcon("icon"));
        add(splitter = newSplitter("splitter"));

        this.label = newLabel("label", labelModel);
        add(label);
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
    * if icon is visible.
    *
    * @param markupId the component id of the splitter
    * @return new splitter component
    */
   protected Component newSplitter(final String markupId) {
       return new WebMarkupContainer(markupId)
               .setRenderBodyOnly(true)
               .setEscapeModelStrings(false);
   }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType() && StringUtils.isNotEmpty(label.getDefaultModelObjectAsString()));
    }

    /**
     * Sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public <L extends Serializable> BootstrapAjaxLink<T> setLabel(IModel<L> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * Sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapAjaxLink<T> setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }

    /**
     * Sets the size.
     */
    public BootstrapAjaxLink<T> setSize(Buttons.Size size) {
        buttonBehavior.setSize(size);

        return this;
    }

    /**
     * Sets the type.
     */
    public BootstrapAjaxLink<T> setType(Buttons.Type type) {
        this.buttonBehavior.setType(type);

        return this;
    }

    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapAjaxLink<T> setBlock(boolean block) {
    	this.buttonBehavior.setBlock(block);
    	
    	return this;
    }

}
