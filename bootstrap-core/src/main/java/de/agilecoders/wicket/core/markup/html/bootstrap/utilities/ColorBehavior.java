package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Adds color utility classes to components.
 * See: https://getbootstrap.com/docs/5.3/utilities/colors/
 *
 * TODO add more colors for BS 5
 * @author Jan Ferko
 */
public class ColorBehavior extends BootstrapBaseBehavior {

    /**
     * Enum of available text colors.
     */
    public enum Color implements ICssClassNameProvider {
        Primary("primary"),
        Primary_emphasis("primary-emphasis"),
        Secondary("secondary"),
        Secondary_emphasis("secondary-emphasis"),
        Success("success"),
        Success_emphasis("success-emphasis"),
        Danger("danger"),
        Danger_emphasis("danger-emphasis"),
        Warning("warning"),
        Warning_emphasis("warning-emphasis"),
        Info("info"),
        Info_emphasis("info-emphasis"),
        Light("light"),
        Light_emphasis("light-emphasis"),
        Dark("dark"),
        Dark_emphasis("dark-emphasis"),
        Body("body"),
        Body_emphasis("body-emphasis"),
        Body_secondary("body-secondary"),
        Body_tertiary("body-tertiary"),
        White("white"),
        Black("black"),
        Black50("black-50"),
        White50("white-50");

        private final String cssClassName;

        Color(String value) {
            this.cssClassName = "text-" + value;
        }

        /**
         * Css class associated with this color.
         * @return Css class associated with this color.
         */
        public String cssClassName() {
            return cssClassName;
        }
    }

    /**
     * Color that should be added to component.
     */
    private final IModel<Color> colorModel;

    /**
     * Constructs new instance for given color.
     * @param color the color that should be added to component.
     */
    public ColorBehavior(Color color) {
    	this(Model.of(color));
    }
    
    /**
	 * @param colorModel
	 */
	public ColorBehavior(IModel<Color> colorModel)
	{
		this.colorModel = colorModel;
	}


	@Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, colorModel.getObject());
    }

    /**
     * Constructs new behavior that adds {@link Color#Primary} to component
     * @return behavior that adds primary color to component
     */
    public static ColorBehavior primary() {
        return new ColorBehavior(Color.Primary);
    }

    /**
     * Constructs new behavior that adds {@link Color#Primary_emphasis} to component
     * @return behavior that adds primary emphasis color to component
     */
    public static ColorBehavior primaryEmphasis() {
        return new ColorBehavior(Color.Primary_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Secondary} to component
     * @return behavior that adds secondary color to component
     */
    public static ColorBehavior secondary() {
        return new ColorBehavior(Color.Secondary);
    }

    /**
     * Constructs new behavior that adds {@link Color#Secondary_emphasis} to component
     * @return behavior that adds secondary emphasis color to component
     */
    public static ColorBehavior secondaryEmphasis() {
        return new ColorBehavior(Color.Secondary_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Success} to component
     * @return behavior that adds success color to component
     */
    public static ColorBehavior success() {
        return new ColorBehavior(Color.Success);
    }

    /**
     * Constructs new behavior that adds {@link Color#Success_emphasis} to component
     * @return behavior that adds success emphasis color to component
     */
    public static ColorBehavior successEmphasis() {
        return new ColorBehavior(Color.Success_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Danger} to component
     * @return behavior that adds danger color to component
     */
    public static ColorBehavior danger() {
        return new ColorBehavior(Color.Danger);
    }

    /**
     * Constructs new behavior that adds {@link Color#Danger_emphasis} to component
     * @return behavior that adds danger emphasis color to component
     */
    public static ColorBehavior dangerEmphasis() {
        return new ColorBehavior(Color.Danger_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Warning} to component
     * @return behavior that adds warning color to component
     */
    public static ColorBehavior warning() {
        return new ColorBehavior(Color.Warning);
    }

    /**
     * Constructs new behavior that adds {@link Color#Warning_emphasis} to component
     * @return behavior that adds warning emphasis color to component
     */
    public static ColorBehavior warningEmphasis() {
        return new ColorBehavior(Color.Warning_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Info} to component
     * @return behavior that adds info color to component
     */
    public static ColorBehavior info() {
        return new ColorBehavior(Color.Info);
    }

    /**
     * Constructs new behavior that adds {@link Color#Info_emphasis} to component
     * @return behavior that adds info emphasis color to component
     */
    public static ColorBehavior infoEmphasis() {
        return new ColorBehavior(Color.Info_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Light} to component
     * @return behavior that adds light color to component
     */
    public static ColorBehavior light() {
        return new ColorBehavior(Color.Light);
    }

    /**
     * Constructs new behavior that adds {@link Color#Light_emphasis} to component
     * @return behavior that adds light emphasis color to component
     */
    public static ColorBehavior lightEmphasis() {
        return new ColorBehavior(Color.Light_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Dark} to component
     * @return behavior that adds dark color to component
     */
    public static ColorBehavior dark() {
        return new ColorBehavior(Color.Dark);
    }

    /**
     * Constructs new behavior that adds {@link Color#Dark_emphasis} to component
     * @return behavior that adds dark emphasis color to component
     */
    public static ColorBehavior darkEmphasis() {
        return new ColorBehavior(Color.Dark_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Body} to component
     * @return behavior that adds body color to component
     */
    public static ColorBehavior body() {
        return new ColorBehavior(Color.Body);
    }

    /**
     * Constructs new behavior that adds {@link Color#Body_emphasis} to component
     * @return behavior that adds body emphasis color to component
     */
    public static ColorBehavior bodyEmphasis() {
        return new ColorBehavior(Color.Body_emphasis);
    }

    /**
     * Constructs new behavior that adds {@link Color#Body_secondary} to component
     * @return behavior that adds body secondary color to component
     */
    public static ColorBehavior bodySecondary() {
        return new ColorBehavior(Color.Body_secondary);
    }

    /**
     * Constructs new behavior that adds {@link Color#Body_tertiary} to component
     * @return behavior that adds body tertiary color to component
     */
    public static ColorBehavior bodyTertiary() {
        return new ColorBehavior(Color.Body_tertiary);
    }

    /**
     * Constructs new behavior that adds {@link Color#White} to component
     * @return behavior that adds white color to component
     */
    public static ColorBehavior white() {
        return new ColorBehavior(Color.White);
    }

    /**
     * Constructs new behavior that adds {@link Color#Black} to component
     * @return behavior that adds black color to component
     */
    public static ColorBehavior black() {
        return new ColorBehavior(Color.Black);
    }

    /**
     * Constructs new behavior that adds {@link Color#Black50} to component
     * @return behavior that adds black-50 color to component
     */
    public static ColorBehavior black50() {
        return new ColorBehavior(Color.Black50);
    }

    /**
     * Constructs new behavior that adds {@link Color#White50} to component
     * @return behavior that adds white-50 color to component
     */
    public static ColorBehavior white50() {
        return new ColorBehavior(Color.White50);
    }
}
