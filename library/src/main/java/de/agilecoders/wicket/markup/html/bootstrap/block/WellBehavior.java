package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;

/**
 * Use the well as a simple effect on an element to give it an inset effect.
 * Control padding and rounded corners with two optional modifier classes ({@link Size}).
 *
 * @author miha
 * @version 1.0
 */
public class WellBehavior extends BootstrapBaseBehavior {

    private final Size size;

    /**
     * defines the size of a well
     */
    public enum Size implements CssClassNameProvider {
        Default, Small, Large;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "well-" + name().toLowerCase();
        }

        @Override
        public AttributeModifier newCssClassNameModifier() {
            return new CssClassNameAppender(this);
        }
    }

    /**
     * Construct.
     *
     * This constructor uses the default well size.
     */
    public WellBehavior() {
        this(Size.Default);
    }

    /**
     * Construct.
     *
     * @param size The size of the well
     */
    public WellBehavior(Size size) {
        this.size = size;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new CssClassNameAppender("well", size.cssClassName()));
    }
}
