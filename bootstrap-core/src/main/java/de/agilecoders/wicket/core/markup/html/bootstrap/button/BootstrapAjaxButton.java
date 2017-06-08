package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * Default {@link AjaxButton} which is styled by bootstrap.
 *
 * @author miha
 */
public abstract class BootstrapAjaxButton extends AjaxButton implements IBootstrapButton<BootstrapAjaxButton> {

    private final Icon icon;
    private final Component label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param type        The type of the button
     */
    public BootstrapAjaxButton(final String componentId, final Buttons.Type type) {
        this(componentId, new Model<String>(), type);
    }

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param model       The label
     * @param type        The type of button
     */
    public BootstrapAjaxButton(final String componentId, final IModel<String> model, final Buttons.Type type) {
        this(componentId, model, null, type);
    }

    /**
     * Construct.
     *
     * @param id   The component id
     * @param form The assigned form
     * @param type The type of button
     */
    public BootstrapAjaxButton(String id, Form<?> form, Buttons.Type type) {
        this(id, Model.<String>of(), form, type);
    }

    /**
     * Construct.
     *
     * @param id    The component id
     * @param model The label
     * @param form  The assigned form
     * @param type  The type of button
     */
    public BootstrapAjaxButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form);

        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));

        add(icon = newIcon("icon"));
        add(splitter = newSplitter("splitter"));
        add(label = newLabel("label", model));
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
    protected final IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType() && StringUtils.isNotEmpty(label.getDefaultModelObjectAsString()));
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    @Override
    public BootstrapAjaxButton setLabel(IModel<String> label) {
        this.label.setDefaultModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapAjaxButton setIconType(IconType iconType) {
        icon.setType(iconType);
        return this;
    }

    /**
     * sets the size of the button
     *
     * @param size The button size
     * @return this instance for chaining
     */
    public BootstrapAjaxButton setSize(Buttons.Size size) {
        buttonBehavior.setSize(size);
        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param type The type of the button
     * @return this instance for chaining
     */
    public BootstrapAjaxButton setType(Buttons.Type type) {
        this.buttonBehavior.setType(type);
        return this;
    }
    
    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapAjaxButton setBlock(boolean block) {
    	this.buttonBehavior.setBlock(block);
    	
    	return this;
    }
}
