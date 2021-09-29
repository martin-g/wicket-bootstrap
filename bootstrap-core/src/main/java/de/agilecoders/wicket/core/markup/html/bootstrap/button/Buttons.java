package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.CssClassNames;

/**
 * Helper class that holds all special style modification
 * types of a button.
 *
 * @author miha
 */
public final class Buttons {
    /**
     * defines all possible sizes of a button element.
     */
    public enum Size implements ICssClassNameProvider {
        Small("btn-sm"),
        Medium(""), // default button size doesn't need any css class
        Large("btn-lg");

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of this size
         */
        Size(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String cssClassName() {
            return cssClassName;
        }

    }

    /**
     * Make a set of buttons appear vertically or horizontally stacked.
     */
    public enum Orientation implements ICssClassNameProvider {
        Horizontal,
        Vertical;

        @Override
        public String cssClassName() {
            return equals(Horizontal) ? "btn-group" : "btn-group-vertical";
        }
    }

    /**
     * Defines all possible button types.
     * @see <a href="https://getbootstrap.com/docs/5.1/components/buttons/">Buttons</a>
     */
    public enum Type implements ICssClassNameProvider {
        Default("btn-secondary"), // Alias for secondary. Kept for backwards compatibility.
        Secondary("btn-secondary"), // Standard gray button with gradient
        Menu(""), // Menu button which has no default css class name
        Primary("btn-primary"), // Provides extra visual weight and identifies the primary action in a set of buttons
        Info("btn-info"), // Used as an alternate to the default styles
        Success("btn-success"), // Indicates a successful or positive action
        Warning("btn-warning"), // Indicates caution should be taken with this action
        Danger("btn-danger"), // Indicates a dangerous or potentially negative action
        Link("btn-link"), // Deemphasize a button by making it look like a link while maintaining button behavior
        Light("btn-light"),
        Dark("btn-dark"),
        Outline_Primary("btn-outline-primary"),
        Outline_Secondary("btn-outline-secondary"),
        Outline_Success("btn-outline-success"),
        Outline_Danger("btn-outline-danger"),
        Outline_Warning("btn-outline-warning"),
        Outline_Info("btn-outline-info"),
        Outline_Light("btn-outline-light"),
        Outline_Dark("btn-outline-dark"),
        NavLink("nav-link"); // type for Navbar buttons and links

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of button type
         */
        Type(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        /**
         * @return css class name of button type
         */
        @Override
        public String cssClassName() {
            return cssClassName;
        }

    }

    /**
     * helper method that adds all necessary css styles to given component tag.
     *
     * @param component          the component to which given tag belongs, needed because of enabled state
     * @param tag                the component tag
     * @param classNameProviders all css class names to add
     */
    public static void onComponentTag(final Component component, final ComponentTag tag, final ICssClassNameProvider... classNameProviders) {
        Args.notNull(classNameProviders, "classNameProviders");

        final CssClassNames.Builder builder = CssClassNames.newBuilder().add(
                "btn", (component.isEnabledInHierarchy() ? "" : "disabled"));

        if (!component.isEnabled() && Components.hasTagName(tag, Set.of("a"))) {
            builder.add("disabled");
            Attributes.set(tag, "aria-disabled", "true");
        }
        for (final ICssClassNameProvider provider : classNameProviders) {
            builder.add(provider.cssClassName());
        }

        Attributes.addClass(tag, builder.asString());
    }

    /**
     * private constructor to prevent instantiation
     */
    private Buttons() {
        throw new UnsupportedOperationException();
    }
}
