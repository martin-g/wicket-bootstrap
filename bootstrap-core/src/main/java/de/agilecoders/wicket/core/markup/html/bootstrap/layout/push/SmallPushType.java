package de.agilecoders.wicket.core.markup.html.bootstrap.layout.push;

/**
 * Pushes for tablets.
 */
public enum SmallPushType implements PushType {

    PUSH0,
    PUSH1, PUSH2, PUSH3, PUSH4,
    PUSH5, PUSH6, PUSH7, PUSH8,
    PUSH9, PUSH10, PUSH11, PUSH12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private SmallPushType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PUSH0) ? "" : "col-sm-push-" + cssClassName.replace("push", "");
    }

}
