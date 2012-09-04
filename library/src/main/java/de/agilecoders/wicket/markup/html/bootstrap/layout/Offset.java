package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * References all available offsets.
 */
public enum Offset implements CssClassNameProvider {

    OFFSET0,
    OFFSET1, OFFSET2, OFFSET3, OFFSET4,
    OFFSET5, OFFSET6, OFFSET7, OFFSET8,
    OFFSET9, OFFSET10, OFFSET11, OFFSET12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private Offset() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(OFFSET0) ? "" : cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender(cssClassName());
    }
}