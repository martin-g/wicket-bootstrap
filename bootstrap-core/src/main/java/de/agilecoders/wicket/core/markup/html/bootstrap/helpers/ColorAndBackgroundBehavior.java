package de.agilecoders.wicket.core.markup.html.bootstrap.helpers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Color and background helpers combine the power of our .text-* utilities and .bg-* utilities in one class.
 */
public class ColorAndBackgroundBehavior extends BootstrapBaseBehavior {

    /**
     * Enumeration of all possible background colors.
     */
    public enum Color {
        Primary("primary"),
        Secondary("secondary"),
        Success("success"),
        Danger("danger"),
        Warning("warning"),
        Info("info"),
        Light("light"),
        Dark("dark");

        private final String value;

        Color(String value) {
            this.value = value;
        }

        /**
         * Css class associated with this background color.
         * @return Css class associated with this background color.
         */
        public String cssClassName() {
            return String.format("text-bg-%s", value);
        }
    }

    /**
     * Background color that should be added to component.
     */
    private Color color;


    /**
     * Constructs new instance for given color.
     * @param color the background color that should be added to component.
     */
    public ColorAndBackgroundBehavior(Color color) {
        this.color = color;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, color.cssClassName());
    }

    public void color(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
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
