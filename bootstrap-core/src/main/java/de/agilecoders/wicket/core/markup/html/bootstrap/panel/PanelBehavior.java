package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * #### Description
 * 
 * A Panel is a box that surrounds your DOM. It has an optional Panel Header/Title, a Panel Body, an extension 
 * point to seamlessly insert Bootstrap Tables and ListGroups and an optional Footer.
 *  
 * The Panel can also be contextualized with a {@link PanelType}.
 * 
 * #### Usage
 *
 * ```java
 * // use constructor to set type:
 * component.add(new PanelBehavior(PanelType.Success));
 * // use setter to set type:
 * component.add(new PanelBehavior()
 *                  .setType(PanelType.Success));
 * ```
 *
 * ```html
 * <div wicket:id="componentId">content</div>
 * ```
 * 
 * Take note that if only this behavior is used, only the top level <div> will be style. The <div> content must be manually implemented.
 * 
 * See {@link BootstrapGenericPanel} for a convenient implementation of the Bootstrap Panel.
 * 
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public class PanelBehavior extends Behavior {
	
	 private final IModel<PanelType> type;

	    /**
	     * Construct.
	     */
	    public PanelBehavior() {
	        this(PanelType.Default);
	    }

	    /**
	     * Construct.
	     *
	     * @param type The type of the Panel
	     */
	    public PanelBehavior(final PanelType type) {
	        this(Model.of(type));
	    }

	    /**
	     * Construct.
	     *
	     * @param type The type of the Panel as model
	     */
	    public PanelBehavior(final IModel<PanelType> type) {
	        super();

	        this.type = type;
	    }

	    @Override
	    public void onComponentTag(final Component component, final ComponentTag tag) {
	        super.onComponentTag(component, tag);

	        Components.assertTag(component, tag, "div");
	        
	        //Clear any previous PanelType css 
	        for(PanelType panelType : Arrays.asList(PanelType.values())){
	        	Attributes.removeClass(tag, panelType.cssClassName());	
	        }
	        	        
	        Attributes.addClass(tag, className(), getType().cssClassName());
	    }

	    /**
	     * @return current Panel type
	     */
	    public final PanelType getType() {
	        return type.getObject();
	    }

	    /**
	     * sets the Panel type
	     *
	     * @param type the Panel type
	     * @return this instance
	     */
	    public final PanelBehavior setType(final PanelType type) {
	        this.type.setObject(type);

	        return this;
	    }

	    /**
	     * @return the css class name as string
	     */
	    protected String className() {
	        return "panel";
	    }

	    @Override
	    public void bind(final Component component) {
	        super.bind(component);

	        BootstrapBaseBehavior.addTo(component);
	    }

	    @Override
	    public void unbind(final Component component) {
	        super.unbind(component);

	        BootstrapBaseBehavior.removeFrom(component);
	    }
}