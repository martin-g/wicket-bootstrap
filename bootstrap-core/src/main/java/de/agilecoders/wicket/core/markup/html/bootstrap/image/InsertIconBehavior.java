package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * An Icon component displays a non localizable image (icon) resource.
 * If attached to a component like 
 * 
 * <pre>
 * 		&lt;button wicket:id="bla">
 * 			My Button
 * 		&lt;/button>
 * </pre>
 * 
 * it will generate a markup.
 *
 *  <pre>
 * 		&lt;button wicket:id="bla">
 * 			<i class="some class"></i>
 * 			My Button
 * 		&lt;/button>
 * </pre>
 * 
 * So no markup is needed for &lt;i> tag.
 *
 * @author reiern70@gmail.com	
 */
public class InsertIconBehavior extends Behavior {

    private final IModel<IconType> type;
    private final IModel<String> value;

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     */
    public InsertIconBehavior(final IconType type) {
        this(Model.of(type));
    }

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     */
    public InsertIconBehavior(final IModel<IconType> type) {
        super();

        this.type = type;
        this.value = Model.of("");
    }


    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);
        if (hasIconType()) {
            value.setObject(type.getObject().cssClassName());
        } else {
            value.setObject("");
        }
    }
    
    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
    	if(hasIconType()) {
    		component.getResponse().write("<i class=\""+value.getObject()+"\"></i>");
    	}
    }

    /**
     * @return true, if an {@link IconType} is set
     */
    public final boolean hasIconType() {
        return type != null && type.getObject() != null;
    }


    /**
     * sets a new icon type
     *
     * @return this instance for chaining
     */
    public final InsertIconBehavior setType(final IconType iconType) {
        type.setObject(iconType);
        return this;
    }

    /**
     * @return the current icon type
     */
    public final IconType type() {
        return type.getObject();
    }


    @Override
    public void detach(Component component) {
        super.detach(component);

        type.detach();
        value.detach();
    }
}
