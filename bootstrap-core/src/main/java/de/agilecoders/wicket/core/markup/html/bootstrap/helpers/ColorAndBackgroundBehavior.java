package de.agilecoders.wicket.core.markup.html.bootstrap.helpers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.Color;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Color and background helpers combine the power of our .text-* utilities and .bg-* utilities in one class.
 */
public class ColorAndBackgroundBehavior extends BootstrapBaseBehavior {

    /**
     * Enumeration of all possible background colors.
     */
    public enum Color implements ICssClassNameProvider {
        Primary("primary"),
        Secondary("secondary"),
        Success("success"),
        Danger("danger"),
        Warning("warning"),
        Info("info"),
        Light("light"),
        Dark("dark");

        private final String cssClassName;

        Color(String value) {
            this.cssClassName = "text-bg-" + value;
        }

        /**
         * Css class associated with this background color.
         * @return Css class associated with this background color.
         */
        public String cssClassName() {
            return cssClassName;
        }
    }

    /**
     * Color that should be added to component.
     */
    private IModel<Color> colorModel;

    /**
     * Constructs new instance for given color.
     * @param color the color that should be added to component.
     */
    public ColorAndBackgroundBehavior(Color color) {
    	this(Model.of(color));
    }
    
    /**
	 * @param colorModel
	 */
	public ColorAndBackgroundBehavior(IModel<Color> colorModel)
	{
		this.colorModel = colorModel;
	}

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, colorModel.getObject());
    }

	/**
	 * Sets color.
	 * @param color
	 * @return this for chaining
	 */
    public ColorAndBackgroundBehavior color(Color color) {
        colorModel.setObject(color);
        return this;
    }
    
    /**
     * Sets color model.
     * @param colorModel
     * @return this for chaining
     */
    public ColorAndBackgroundBehavior color(IModel<Color> colorModel) {
    	this.colorModel = colorModel;
    	return this;
    }

    /**
     * @return color
     */
    public Color getColor() {
    	return colorModel.getObject();
    }
    
    /**
     * @return color model
     */
    public IModel<Color> getColorModel() {
        return colorModel;
    }
    
    
    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Primary} to component
     *
     * @return behavior that adds primary background color to component
     */
    public static ColorAndBackgroundBehavior primary() {
        return new ColorAndBackgroundBehavior(Color.Primary);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Secondary} to component
     *
     * @return behavior that adds secondary background color to component
     */
    public static ColorAndBackgroundBehavior secondary() {
        return new ColorAndBackgroundBehavior(Color.Secondary);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Success} to component
     *
     * @return behavior that adds success background color to component
     */
    public static ColorAndBackgroundBehavior success() {
        return new ColorAndBackgroundBehavior(Color.Success);
    }
    
    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Danger} to component
     *
     * @return behavior that adds danger background color to component
     */
    public static ColorAndBackgroundBehavior danger() {
        return new ColorAndBackgroundBehavior(Color.Danger);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Warning} to component
     *
     * @return behavior that adds warning background color to component
     */
    public static ColorAndBackgroundBehavior warning() {
        return new ColorAndBackgroundBehavior(Color.Warning);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Info} to component
     *
     * @return behavior that adds info background color to component
     */
    public static ColorAndBackgroundBehavior info() {
        return new ColorAndBackgroundBehavior(Color.Info);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Light} to component
     *
     * @return behavior that adds light background color to component
     */
    public static ColorAndBackgroundBehavior light() {
        return new ColorAndBackgroundBehavior(Color.Light);
    }

    /**
     * Constructs new behavior that adds {@link ColorAndBackgroundBehavior.Color#Dark} to component
     *
     * @return behavior that adds dark background color to component
     */
    public static ColorAndBackgroundBehavior dark() {
        return new ColorAndBackgroundBehavior(Color.Dark);
    }
}
