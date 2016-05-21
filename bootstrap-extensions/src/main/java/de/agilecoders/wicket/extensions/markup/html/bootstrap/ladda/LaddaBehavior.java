package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.SpinJsReference;

/**
 * A behavior that applies the CSS classes required by <a href="http://msurguy.github.io/ladda-bootstrap/">Ladda UI</a>.
 */
public class LaddaBehavior extends Behavior {

    /**
     * The available UI effects
     */
    public enum Effect implements ICssClassNameProvider {
        EXPAND_LEFT, EXPAND_RIGHT, EXPAND_UP, EXPAND_DOWN,
        ZOOM_IN, ZOOM_OUT,
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN,
        CONTRACT;

        @Override
        public String cssClassName() {
            return name().toLowerCase().replace('_', '-');
        }
    }

    /**
     * The effect to use
     */
    private Effect effect = Effect.ZOOM_OUT;

    /**
     * The color of the spinner. E.g. "#003366"
     */
    private String spinnerColor;

    /**
     * The size of the spinner in pixels
     */
    private int spinnerSize;

    /**
     * Sets the effect to use
     *
     * @param effect The effect to use
     * @return {@code this}, for chaining
     */
    public LaddaBehavior withEffect(Effect effect) {
        this.effect = Args.notNull(effect, "effect");
        return this;
    }

    /**
     * Sets the color for the spinner
     *
     * @param color The color for the spinner
     * @return {@code this}, for chaining
     */
    public LaddaBehavior withSpinnerColor(String color) {
        this.spinnerColor = color;
        return this;
    }

    /**
     * Sets the size of the spinner in pixels
     *
     * @param size The size of the spinner in pixels
     * @return {@code this}, for chaining
     */
    public LaddaBehavior withSpinnerSize(int size) {
        this.spinnerSize = size;
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "ladda-button");
        Attributes.set(tag, "data-style", effect.cssClassName());

        if (!Strings.isEmpty(spinnerColor)) {
            Attributes.set(tag, "data-spinner-color", spinnerColor);
        }

        if (spinnerSize > 0) {
            Attributes.set(tag, "data-spinner-size", String.valueOf(spinnerSize));
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(LaddaCssReference.INSTANCE));

        response.render(JavaScriptHeaderItem.forReference(SpinJsReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(LaddaJsReference.INSTANCE));
    }
}
