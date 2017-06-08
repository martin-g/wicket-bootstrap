package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * A bootstrap styled {@link Button}
 *
 * @author miha
 */
public class BootstrapButton extends Button implements IBootstrapButton<BootstrapButton> {

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
    protected Component newLabel(final String markupId, IModel<String> model) {
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

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType() && StringUtils.isNotEmpty(getModelObject()));
    }

    /**
     * {@inheritDoc}
     */
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
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return reference to the current instance
     */
    public BootstrapButton setIconType(final IconType iconType) {
        this.icon.setType(iconType);

        return this;
    }

    public BootstrapButton setSize(Buttons.Size size) {
        this.buttonBehavior.setSize(size);

        return this;
    }

    @Override
    public BootstrapButton setType(Buttons.Type type) {
        this.buttonBehavior.setType(type);

        return this;
    }
    
    
    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapButton setBlock(boolean block) {
    	this.buttonBehavior.setBlock(block);
    	
    	return this;
    }

}
