package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 *
 */
public class LaddaBehavior extends Behavior {

    public static enum Size {
        XS, S, M, L, XL
    }

    public static enum Style implements ICssClassNameProvider {
        EXPAND_LEFT, EXPAND_RIGHT, EXPAND_UP, EXPAND_DOWN,
        ZOOM_IN, ZOOM_OUT,
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN,
        CONTACT;

        @Override
        public String cssClassName() {
            return name().toLowerCase().replace('_', '-');
        }
    }

    private Style style = Style.ZOOM_OUT;

    private Size size = Size.M;

    private String spinnerColor;

    private int spinnerSize;

    public LaddaBehavior withStyle(Style style) {
        this.style = Args.notNull(style, "style");
        return this;
    }

    public LaddaBehavior withSize(Size size) {
        this.size = size;
        return this;
    }

    public LaddaBehavior withSpinnerColor(String color) {
        this.spinnerColor = color;
        return this;
    }

    public LaddaBehavior withSpinnerSize(int size) {
        this.spinnerSize = size;
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "ladda-button");
        Attributes.set(tag, "data-style", style.cssClassName());
        if (size != Size.M) {
            Attributes.set(tag, "data-size", size.name().toLowerCase());
        }

        if (!Strings.isEmpty(spinnerColor)) {
            Attributes.set(tag, "data-spinner-color", spinnerColor);
        }

        if (spinnerSize != 0) {
            Attributes.set(tag, "data-spinner-size", String.valueOf(spinnerSize));
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(LaddaBehavior.class, "css/ladda-themeless.css")));

        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(LaddaBehavior.class, "js/spin.js")));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(LaddaBehavior.class, "js/ladda.js")));
    }
}
