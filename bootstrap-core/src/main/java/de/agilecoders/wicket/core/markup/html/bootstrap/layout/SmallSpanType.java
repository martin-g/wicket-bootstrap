package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

/**
 * References all available spans.
 */
public enum SmallSpanType implements SpanType {

    SPAN1, SPAN2, SPAN3, SPAN4,
    SPAN5, SPAN6, SPAN7, SPAN8,
    SPAN9, SPAN10, SPAN11, SPAN12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private SmallSpanType() {
        this.cssClassName = "col-sm-" + name().toLowerCase().replace("span", "");
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

}