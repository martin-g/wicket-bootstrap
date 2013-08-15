package de.agilecoders.wicket.core.markup.html.bootstrap.layout.col;

/**
 * Span types for phones.
 */
public enum ExtraSmallSpanType implements SpanType {
    SPAN1, SPAN2, SPAN3, SPAN4,
    SPAN5, SPAN6, SPAN7, SPAN8,
    SPAN9, SPAN10, SPAN11, SPAN12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private ExtraSmallSpanType() {
        this.cssClassName = "col-xs-" + name().toLowerCase().replace("span", "");
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }
}
