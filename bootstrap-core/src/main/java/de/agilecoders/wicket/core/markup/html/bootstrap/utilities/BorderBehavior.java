package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
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
    public enum Color {
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

        private final String value;

        Color(String value) {
            this.value = value;
        }

        /**
         * Returns css class name of given border color
         */
        public String cssClassName() {
            return String.format("border-%s", value);
        }
    }

    /**
     * Classes to easily round its corners.
     */
    public enum Radius {
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
    public enum Type {
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
     * Border color that should be added to component.
     */
    private BorderBehavior.Color color;

    /**
     * Border rounding type.
     */
    private BorderBehavior.Radius radius;

    /**
     * Type of border.
     */
    private BorderBehavior.Type type;

    /**
     * Constructs new instance of default border.
     */
    public BorderBehavior() {
        this.color = Color.Secondary;
        this.radius = Radius.None;
        this.type = Type.None;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, type.cssClassName(), color.cssClassName(), radius.cssClassName());
    }

    /**
     * Sets new border color.
     *
     * @param color the new border color
     */
    public BorderBehavior color(Color color) {
        this.color = color;

        return this;
    }

    /**
     * Sets new type of rounding border corners.
     *
     * @param radius the new rounding border corners type.
     */
    public BorderBehavior radius(Radius radius) {
        this.radius = radius;

        return this;
    }

    /**
     * Sets new border type.
     *
     * @param type the type of border
     */
    public BorderBehavior type(Type type) {
        this.type = type;

        return this;
    }
}
