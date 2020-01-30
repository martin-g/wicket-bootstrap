package de.agilecoders.wicket.extensions.markup.html.bootstrap.spinner;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * A behavior that applies the CSS classes required by <a href="http://msurguy.github.io/ladda-bootstrap/">Ladda UI</a>.
 */
public class SpinnerBehavior extends Behavior {
    private static final long serialVersionUID = 1L;

    /**
     * The available UI effects
     */
    public enum Effect implements ICssClassNameProvider {
        SPINNER_BORDER, SPINNER_GROW;

        @Override
        public String cssClassName() {
            return name().toLowerCase().replace('_', '-');
        }
    }

    /**
     * The available colors
     */
    public enum Color implements ICssClassNameProvider {
        TEXT_PRIMARY, TEXT_SECONDARY, TEXT_SUCCESS,
        TEXT_DANGER, TEXT_WARNING, TEXT_INFO, TEXT_LIGHT,
        TEXT_DARK;

        @Override
        public String cssClassName() {
            return name().toLowerCase().replace('_', '-');
        }
    }

    /**
     * The effect to use
     */
    private Effect effect = Effect.SPINNER_BORDER;

    /**
     * The color of the spinner. E.g. "#003366"
     */
    private Color spinnerColor;

    /**
     * smaller spinner that can quickly be used within other components.
     */
    private boolean small = true;

    /**
     * Sets the effect to use
     *
     * @param effect The effect to use
     * @return {@code this}, for chaining
     */
    public SpinnerBehavior withEffect(Effect effect) {
        this.effect = Args.notNull(effect, "effect");
        return this;
    }

    /**
     * Sets the color for the spinner
     *
     * @param color The color for the spinner
     * @return {@code this}, for chaining
     */
    public SpinnerBehavior withSpinnerColor(Color color) {
        this.spinnerColor = color;
        return this;
    }

    /**
     * Sets if spinner will be small or not
     *
     * @param small The effect to use
     * @return {@code this}, for chaining
     */
    public SpinnerBehavior withSmall(boolean small) {
        this.small = small;
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "ladda-button");
        Attributes.set(tag, "data-style", effect.cssClassName());
        if (small) {
            Attributes.set(tag, "data-spinner-small", "" + small);
        }

        if (spinnerColor != null) {
            Attributes.set(tag, "data-spinner-color", spinnerColor.cssClassName());
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(JavaScriptHeaderItem.forReference(SpinnerJsReference.INSTANCE));
    }
}
