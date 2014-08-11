package de.agilecoders.wicket.core.markup.html.bootstrap.layout.push;

/**
 * Pushes for (non-large) desktops.
 */
public enum MediumPushType implements PushType {

    PUSH0,
    PUSH1, PUSH2, PUSH3, PUSH4,
    PUSH5, PUSH6, PUSH7, PUSH8,
    PUSH9, PUSH10, PUSH11, PUSH12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private MediumPushType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PUSH0) ? "" : "col-md-push-" + cssClassName.replace("push", "");
    }

}
