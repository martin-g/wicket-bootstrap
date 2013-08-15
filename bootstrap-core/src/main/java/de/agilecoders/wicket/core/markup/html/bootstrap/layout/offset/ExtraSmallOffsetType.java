package de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset;

/**
 * Offsets for phones.
 */
public enum ExtraSmallOffsetType implements OffsetType {

    OFFSET0,
    OFFSET1, OFFSET2, OFFSET3, OFFSET4,
    OFFSET5, OFFSET6, OFFSET7, OFFSET8,
    OFFSET9, OFFSET10, OFFSET11, OFFSET12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private ExtraSmallOffsetType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(OFFSET0) ? "" : "col-xs-offset-" + cssClassName.replace("offset", "");
    }

}
