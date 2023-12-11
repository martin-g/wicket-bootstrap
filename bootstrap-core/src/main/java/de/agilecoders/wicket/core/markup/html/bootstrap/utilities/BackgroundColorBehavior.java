package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Adds background color utility classes to components.
 *
 * @author Jan Ferko
 */
public class BackgroundColorBehavior extends BootstrapBaseBehavior {

    /**
     * Enumeration of all possible background colors.
     * https://getbootstrap.com/docs/5.3/utilities/background/
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
        Body_secondary("body-secondary"),
        Body_tertiary("body-tertiary"),
        Body("body"),
        Black("black"),
        White("white"),
        Transparent("transparent");

        private final String cssClassName;

        Color(String value) {
            this.cssClassName = "bg-" + value;
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
     * Background color that should be added to component.
     */
    private IModel<Color> colorModel;


    /**
     * Constructs new instance for given color.
     * @param color the background color that should be added to component.
     */
    public BackgroundColorBehavior(Color color) {
        this(Model.of(color));
    }

    
    /**
	 * @param colorModel
	 */
	public BackgroundColorBehavior(IModel<Color> colorModel)
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
    public BackgroundColorBehavior color(Color color) {
        colorModel.setObject(color);
        return this;
    }
    
    /**
     * Sets color model.
     * @param colorModel
     * @return this for chaining
     */
    public BackgroundColorBehavior color(IModel<Color> colorModel) {
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
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Primary} to component
     *
     * @return behavior that adds primary background color to component
     */
    public static BackgroundColorBehavior primary() {
        return new BackgroundColorBehavior(Color.Primary);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Primary_subtle} to component
     *
     * @return behavior that adds primary subtle background color to component
     */
    public static BackgroundColorBehavior primarySubtle() {
        return new BackgroundColorBehavior(Color.Primary_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Secondary} to component
     *
     * @return behavior that adds secondary background color to component
     */
    public static BackgroundColorBehavior secondary() {
        return new BackgroundColorBehavior(Color.Secondary);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Secondary_subtle} to component
     *
     * @return behavior that adds secondary subtle background color to component
     */
    public static BackgroundColorBehavior secondarySubtle() {
        return new BackgroundColorBehavior(Color.Secondary_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Success} to component
     *
     * @return behavior that adds success background color to component
     */
    public static BackgroundColorBehavior success() {
        return new BackgroundColorBehavior(Color.Success);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Success_subtle} to component
     *
     * @return behavior that adds success subtle background color to component
     */
    public static BackgroundColorBehavior successSubtle() {
        return new BackgroundColorBehavior(Color.Success_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Danger} to component
     *
     * @return behavior that adds danger background color to component
     */
    public static BackgroundColorBehavior danger() {
        return new BackgroundColorBehavior(Color.Danger);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Danger_subtle} to component
     *
     * @return behavior that adds danger subtle background color to component
     */
    public static BackgroundColorBehavior dangerSubtle() {
        return new BackgroundColorBehavior(Color.Danger_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Warning} to component
     *
     * @return behavior that adds warning background color to component
     */
    public static BackgroundColorBehavior warning() {
        return new BackgroundColorBehavior(Color.Warning);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Warning_subtle} to component
     *
     * @return behavior that adds warning subtle background color to component
     */
    public static BackgroundColorBehavior warningSubtle() {
        return new BackgroundColorBehavior(Color.Warning_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Info} to component
     *
     * @return behavior that adds info background color to component
     */
    public static BackgroundColorBehavior info() {
        return new BackgroundColorBehavior(Color.Info);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Info_subtle} to component
     *
     * @return behavior that adds info subtle background color to component
     */
    public static BackgroundColorBehavior infoSubtle() {
        return new BackgroundColorBehavior(Color.Info_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Light} to component
     *
     * @return behavior that adds light background color to component
     */
    public static BackgroundColorBehavior light() {
        return new BackgroundColorBehavior(Color.Light);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Light_subtle} to component
     *
     * @return behavior that adds light subtle background color to component
     */
    public static BackgroundColorBehavior lightSubtle() {
        return new BackgroundColorBehavior(Color.Light_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Dark} to component
     *
     * @return behavior that adds dark background color to component
     */
    public static BackgroundColorBehavior dark() {
        return new BackgroundColorBehavior(Color.Dark);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Dark_subtle} to component
     *
     * @return behavior that adds dark subtle background color to component
     */
    public static BackgroundColorBehavior darkSubtle() {
        return new BackgroundColorBehavior(Color.Dark_subtle);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Body_secondary} to component
     *
     * @return behavior that adds body secondary background color to component
     */
    public static BackgroundColorBehavior bodySecondary() {
        return new BackgroundColorBehavior(Color.Body_secondary);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Body_tertiary} to component
     *
     * @return behavior that adds body tertiary background color to component
     */
    public static BackgroundColorBehavior bodyTertiary() {
        return new BackgroundColorBehavior(Color.Body_tertiary);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Body} to component
     *
     * @return behavior that adds body background color to component
     */
    public static BackgroundColorBehavior body() {
        return new BackgroundColorBehavior(Color.Body);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Black} to component
     *
     * @return behavior that adds black background color to component
     */
    public static BackgroundColorBehavior black() {
        return new BackgroundColorBehavior(Color.Black);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#White} to component
     *
     * @return behavior that adds white background color to component
     */
    public static BackgroundColorBehavior white() {
        return new BackgroundColorBehavior(Color.White);
    }

    /**
     * Constructs new behavior that adds {@link BackgroundColorBehavior.Color#Transparent} to component
     *
     * @return behavior that adds transparent background color to component
     */
    public static BackgroundColorBehavior transparent() {
        return new BackgroundColorBehavior(Color.Transparent);
    }

}
