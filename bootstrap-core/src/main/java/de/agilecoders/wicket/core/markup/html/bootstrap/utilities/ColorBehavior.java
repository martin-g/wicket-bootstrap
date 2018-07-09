package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Adds color utility classes to components.
 * See: https://getbootstrap.com/docs/4.1/utilities/colors/#color
 *
 * @author Jan Ferko
 */
public class ColorBehavior extends BootstrapBaseBehavior {

    /**
     * Enum of available text colors.
     */
    public enum Color {
        Primary("primary"),
        Secondary("secondary"),
        Success("success"),
        Danger("danger"),
        Warning("warning"),
        Info("info"),
        Light("light"),
        Dark("dark"),
        Body("body"),
        Muted("muted"),
        White("white"),
        Black("black"),
        Black50("black-50"),
        White50("white-50");

        private final String value;

        Color(String value) {
            this.value = value;
        }

        /**
         * Css class associated with this color.
         * @return Css class associated with this color.
         */
        public String cssClassName() {
            return String.format("text-%s", this.value);
        }
    }

    /**
     * Color that should be added to component.
     */
    private final Color color;

    /**
     * Constructs new instance for given color.
     * @param color the color that should be added to component.
     */
    public ColorBehavior(Color color) {
        this.color = color;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, color.cssClassName());
    }

    /**
     * Constructs new behavior that adds {@link Color#Primary} to component
     * @return behavior that adds primary color to component
     */
    public static ColorBehavior primary() {
        return new ColorBehavior(Color.Primary);
    }

    /**
     * Constructs new behavior that adds {@link Color#Success} to component
     * @return behavior that adds secondary color to component
     */
    public static ColorBehavior secondary() {
        return new ColorBehavior(Color.Secondary);
    }

    /**
     * Constructs new behavior that adds {@link Color#Success} to component
     * @return behavior that adds success color to component
     */
    public static ColorBehavior success() {
        return new ColorBehavior(Color.Success);
    }

    /**
     * Constructs new behavior that adds {@link Color#Danger} to component
     * @return behavior that adds danger color to component
     */
    public static ColorBehavior danger() {
        return new ColorBehavior(Color.Danger);
    }

    /**
     * Constructs new behavior that adds {@link Color#Warning} to component
     * @return behavior that adds warning color to component
     */
    public static ColorBehavior warning() {
        return new ColorBehavior(Color.Warning);
    }

    /**
     * Constructs new behavior that adds {@link Color#Info} to component
     * @return behavior that adds info color to component
     */
    public static ColorBehavior info() {
        return new ColorBehavior(Color.Info);
    }

    /**
     * Constructs new behavior that adds {@link Color#Light} to component
     * @return behavior that adds light color to component
     */
    public static ColorBehavior light() {
        return new ColorBehavior(Color.Light);
    }

    /**
     * Constructs new behavior that adds {@link Color#Dark} to component
     * @return behavior that adds dark color to component
     */
    public static ColorBehavior dark() {
        return new ColorBehavior(Color.Dark);
    }

    /**
     * Constructs new behavior that adds {@link Color#Body} to component
     * @return behavior that adds body color to component
     */
    public static ColorBehavior body() {
        return new ColorBehavior(Color.Body);
    }

    /**
     * Constructs new behavior that adds {@link Color#Muted} to component
     * @return behavior that adds muted color to component
     */
    public static ColorBehavior muted() {
        return new ColorBehavior(Color.Muted);
    }

    /**
     * Constructs new behavior that adds {@link Color#White} to component
     * @return behavior that adds white color to component
     */
    public static ColorBehavior white() {
        return new ColorBehavior(Color.White);
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
     *
     * @return behavior that adds white-50 color to component
     */
    public static ColorBehavior white50() {
        return new ColorBehavior(Color.White50);
    }
}
