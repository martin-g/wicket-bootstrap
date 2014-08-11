package de.agilecoders.wicket.core.markup.html.bootstrap.layout.push;

/**
 * Pushes for large desktops.
 */
public enum ExtraLargePushType implements PushType {

    PUSH0,
    PUSH1, PUSH2, PUSH3, PUSH4,
    PUSH5, PUSH6, PUSH7, PUSH8,
    PUSH9, PUSH10, PUSH11, PUSH12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private ExtraLargePushType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PUSH0) ? "" : "col-lg-push-" + cssClassName.replace("push", "");
    }

}
