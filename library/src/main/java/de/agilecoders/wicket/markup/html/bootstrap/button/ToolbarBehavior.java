package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.Component;

/**
 * Combine sets of <div class="btn-group"> into a <div class="btn-toolbar"> for more complex components.
 *
 * @author miha
 * @version 1.0
 */
public final class ToolbarBehavior extends BootstrapBaseBehavior {

    /**
     * Make a set of buttons appear vertically or horizontally stacked.
     */
    public enum Orientation implements CssClassNameProvider {
        Horizontal, Vertical;

        @Override
        public String cssClassName() {
            return equals(Horizontal) ? "" : "btn-group-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(this);
        }
    }

    private final Orientation orientation;

    /**
     * Construct.
     */
    public ToolbarBehavior() {
        this(Orientation.Horizontal);
    }

    /**
     * Construct.
     *
     * @param orientation Make a set of buttons appear vertically stacked rather than horizontally if set to {@link Orientation#Vertical}.
     */
    public ToolbarBehavior(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("div"));
        component.add(new CssClassNameAppender("btn-toolbar", orientation.cssClassName()));
    }
}