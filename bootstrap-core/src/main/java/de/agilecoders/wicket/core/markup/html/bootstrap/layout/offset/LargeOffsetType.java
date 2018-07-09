package de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset;

/**
 * Offsets for large desktops.
 */
public enum LargeOffsetType implements OffsetType {

    OFFSET0,
    OFFSET1, OFFSET2, OFFSET3, OFFSET4,
    OFFSET5, OFFSET6, OFFSET7, OFFSET8,
    OFFSET9, OFFSET10, OFFSET11, OFFSET12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private LargeOffsetType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(OFFSET0) ? "" : "offset-lg-" + cssClassName.replace("offset", "");
    }

}
