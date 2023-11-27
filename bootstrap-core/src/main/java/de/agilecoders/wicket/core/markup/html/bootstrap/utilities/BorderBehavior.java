package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Behavior that adds different border utility classes to components.
 * https://getbootstrap.com/docs/5.3/utilities/borders/
 *
 * @author Jan Ferko
 */
public class BorderBehavior extends BootstrapBaseBehavior {

    /**
     * Border colors built on our theme colors.
     */
    public enum Color implements ICssClassNameProvider {
        Primary("primary"),
        Primary_subtle("primary-subtle"),
        Secondary("secondary"),
        Secondary_subtle("secondary-subtle"),
        Success("success"),
        Success_subtle("success-subtle"),
        Danger("danger"),
        Danger_subtle("danger-subtle"),
        Warning("warning"),
        Warning_subtle("warning-subtle"),
        Info("info"),
        Info_subtle("info-subtle"),
        Light("light"),
        Light_subtle("light-subtle"),
        Dark("dark"),
        Dark_subtle("dark-subtle"),
        Black("black"),
        White("white");

        private final String cssClassName;

        Color(String value) {
        	cssClassName = "border-" + value;
        }

        /**
         * Returns css class name of given border color
         */
        public String cssClassName() {
            return cssClassName;
        }
    }

    /**
     * Classes to easily round its corners.
     */
    public enum Radius implements ICssClassNameProvider {
        All("rounded"),
        Top("rounded-top"),
        Right("rounded-end"),
        Bottom("rounded-bottom"),
        Left("rounded-start"),
        Circle("rounded-circle"),
        Pill("rounded-pill"),
        None("rounded-0");

        private final String value;

        Radius(String value) {
            this.value = value;
        }

        /**
         * Returns css class of this border radius.
         */
        public String cssClassName() {
            return value;
        }
    }

    /**
     * Enum of available border types.
     */
    public enum Type implements ICssClassNameProvider {
        All("border"),
        Top("border-top"),
        Right("border-end"),
        Bottom("border-bottom"),
        Left("border-start"),
        ExceptTop("border-top-0"),
        ExceptRight("border-end-0"),
        ExceptBottom("border-bottom-0"),
        ExceptLeft("border-start-0"),
        None("border-0");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        /**
         * Returns css class of given border type.
         */
        public String cssClassName() {
            return value;
        }
    }
    
    /**
     * Enum of available border types.
     */
    public enum Width implements ICssClassNameProvider {
    	Width_1(1),
    	Width_2(2),
    	Width_3(3),
    	Width_4(4),
    	Width_5(5)
    	;
    	
    	private final String cssClassName;
    	
    	Width(int width) {
    		this.cssClassName = "border-" + width;
    	}
    	
    	/**
    	 * Returns css class of given border type.
    	 */
    	public String cssClassName() {
    		return cssClassName;
    	}
    }

    /**
     * Border color that should be added to component.
     */
    private IModel<BorderBehavior.Color> colorModel;

    /**
     * Border rounding type.
     */
    private IModel<BorderBehavior.Radius> radiusModel;

    /**
     * Type of border.
     */
    private IModel<BorderBehavior.Type> typeModel;

    /**
     * Width of border.
     */
    private IModel<BorderBehavior.Width> widthModel;

    /**
     * Constructs new instance of default border.
     */
    public BorderBehavior() {
        this.colorModel = Model.of(Color.Secondary);
        this.radiusModel = Model.of(Radius.None);
        this.typeModel = Model.of(Type.None);
        this.widthModel = Model.of();
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, colorModel.getObject());
        Attributes.addClass(tag, radiusModel.getObject());
        Attributes.addClass(tag, typeModel.getObject());
        Attributes.addClass(tag, widthModel.getObject());
    }
    
    /**
     * Sets new border color.
     *
     * @param color the new border color
     */
    public BorderBehavior color(Color color) {
        colorModel.setObject(color);
        return this;
    }
    
    /**
     * Sets new border color.
     *
     * @param colorModel the new border color
     */
    public BorderBehavior color(IModel<Color> colorModel) {
        this.colorModel = colorModel;
        return this;
    }

    /**
     * Sets new type of rounding border corners.
     *
     * @param radius the new rounding border corners type.
     */
    public BorderBehavior radius(Radius radius) {
        radiusModel.setObject(radius);
        return this;
    }

    /**
     * Sets new type of rounding border corners.
     *
     * @param radiusModel the model for the rounding border corners type
     */
    public BorderBehavior radius(IModel<Radius> radiusModel) {
        this.radiusModel = radiusModel;
        return this;
    }

    /**
     * Sets new border type.
     *
     * @param type the type of border
     */
    public BorderBehavior type(Type type) {
        typeModel.setObject(type);
        return this;
    }

    /**
     * Sets new border type.
     *
     * @param typeModel the model for the type of border
     */
    public BorderBehavior type(IModel<Type> typeModel) {
        this.typeModel = typeModel;
        return this;
    }

    /**
     * Sets new border width.
     *
     * @param width the width of the border
     */
    public BorderBehavior width(Width width) {
        widthModel.setObject(width);
        return this;
    }

    /**
     * Sets new border width.
     *
     * @param widthModel the model for the width of the border
     */
    public BorderBehavior width(IModel<Width> widthModel) {
        this.widthModel = widthModel;
        return this;
    }
}
