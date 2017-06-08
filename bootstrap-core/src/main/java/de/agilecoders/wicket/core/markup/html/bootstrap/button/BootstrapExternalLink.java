package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.util.Models;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default {@link org.apache.wicket.markup.html.link.ExternalLink} which is styled by bootstrap.
 *
 * @author miha
 */
public abstract class BootstrapExternalLink extends ExternalLink implements IBootstrapButton<BootstrapExternalLink> {

    /**
     * The target attribute specifies where to open the linked document.
     */
    public enum Target {
        // Opens the linked document in a new window or tab
        blank,
        // Opens the linked document in the same frame as it was clicked (this is default)
        self,
        // Opens the linked document in the parent frame
        parent,
        // Opens the linked document in the full body of the window
        top,

        // removes the target attribute
        none
    }

    private final Icon icon;
    private final Component label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;
    private final IModel<String> target;

    /**
     * Construct.
     *
     * @param id   the components id
     * @param href mandatory parameter
     */
    public BootstrapExternalLink(final String id, final IModel<String> href) {
        this(id, href, Buttons.Type.Link);
    }

    /**
     * Construct.
     *
     * @param id    The component id
     * @param model mandatory parameter
     * @param type  the type of the button
     */
    public BootstrapExternalLink(final String id, final IModel<String> model, final Buttons.Type type) {
        super(id, model);

        this.target = Model.of("");

        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));

        add(icon = newIcon("icon"));
        add(splitter = newSplitter("splitter"));
        add(label = newLabel("label"));
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
    protected Component newLabel(final String markupId) {
        return new Label(markupId, new Model<String>(""))
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
    protected void onComponentTag(ComponentTag tag) {
        if (!Models.isNullOrEmpty(target)) {
            tag.put("target", target.getObject());
        }

        super.onComponentTag(tag);
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
    public BootstrapExternalLink setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapExternalLink setIconType(IconType iconType) {
        icon.setType(iconType);
        return this;
    }

    /**
     * sets the size of the button
     *
     * @param size The button size
     * @return this instance for chaining
     */
    public BootstrapExternalLink setSize(Buttons.Size size) {
        buttonBehavior.setSize(size);
        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param type The type of the button
     * @return this instance for chaining
     */
    public BootstrapExternalLink setType(Buttons.Type type) {
        this.buttonBehavior.setType(type);
        return this;
    }
    
    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapExternalLink setBlock(boolean block) {
    	this.buttonBehavior.setBlock(block);
    	
    	return this;
    }
    
    /**
     * Sets the target of the link
     *
     * @param target The link target
     * @return this instance for chaining
     */
    public BootstrapExternalLink setTarget(Target target) {
        if (target == null || target == Target.none) {
            this.target.setObject("");
        } else {
            this.target.setObject("_" + target.name());
        }
        return this;
    }

}
