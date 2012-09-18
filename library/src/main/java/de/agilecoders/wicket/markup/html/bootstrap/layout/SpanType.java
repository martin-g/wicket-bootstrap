package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * References all available spans.
 */
public enum SpanType implements CssClassNameProvider {

    SPAN1, SPAN2, SPAN3, SPAN4,
    SPAN5, SPAN6, SPAN7, SPAN8,
    SPAN9, SPAN10, SPAN11, SPAN12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private SpanType() {
        this.cssClassName = "span" + name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }
}