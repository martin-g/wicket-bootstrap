package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
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

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * A link that does client "logic".
 *
 * @author reiern70
 */
public class BootstrapClientSideLink<T> extends AbstractLink implements IBootstrapButton<BootstrapClientSideLink<T>> {

    private final Icon icon;
    private final Component label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    private IModel<String> onclickCode;
    
    /**
     * Construct.
     *
     * @param id   the components id
     * @param type the type of the button
     * @param onclickCode The code to execute on click.
     */
    public BootstrapClientSideLink(final String id, final Buttons.Type type, String onclickCode) {
        this(id, null, type, Model.of(onclickCode));
    }
    
    /**
     * 
     * @param id  the components id
     * @param type the type of the button
     * @param onclickCode  The code to execute on click.
     */
    public BootstrapClientSideLink(final String id, final Buttons.Type type, IModel<String> onclickCode) {
        this(id, null, type, onclickCode);
    }

    /**
     * Construct.
     *
     * @param id    The component id
     * @param model mandatory parameter
     * @param type  the type of the button
     *  @param onclickCode  The code to execute on click.
     */
    public BootstrapClientSideLink(String id, IModel<T> model, Buttons.Type type, IModel<String> onclickCode) {
        super(id, model);
        
        setOutputMarkupId(true);
        this.onclickCode = onclickCode;
        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));
        add(icon = newIcon("icon"));
        add(splitter = newSplitter("splitter"));        
        add(label = newLabel("label", model)); 
    }
    
    @Override
    protected void onComponentTag(ComponentTag tag) {
    	super.onComponentTag(tag);
    	tag.put("href",  getTarget().getObject());
    }
    
    @Override
    protected void detachModel() {
    	super.detachModel();
    	onclickCode.detach();
    }
    
    /**
     * @return returns the target.
     */
    protected IModel<String> getTarget() {
    	return Model.of("javascript:void(0)");
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
    	String script = onclickCode.getObject();
    	StringBuilder builder = new StringBuilder();
    	builder.append("$('#").append(getOutputMarkupId()).append("').on('click', function(e) {\n")
    	.append(!Strings.isEmpty(script)?script:"").append("; e.preventDefault();\n});");
    	response.render(OnDomReadyHeaderItem.forScript(builder));
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
    protected Component newLabel(final String markupId, IModel<T> model) {
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
    public BootstrapClientSideLink<T> setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * Sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapClientSideLink<T> setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }
   
    /**
     * Sets the size.
     */
    public BootstrapClientSideLink<T> setSize(Buttons.Size size) {
        buttonBehavior.setSize(size);

        return this;
    }

    /**
     * Sets the type.
     */
    public BootstrapClientSideLink<T> setType(Buttons.Type type) {
        this.buttonBehavior.setType(type);

        return this;
    }

}
