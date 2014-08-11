package de.agilecoders.wicket.core.markup.html.bootstrap.layout.push;

/**
 * Pushes for phones.
 */
public enum ExtraSmallPushType implements PushType {

    PUSH0,
    PUSH1, PUSH2, PUSH3, PUSH4,
    PUSH5, PUSH6, PUSH7, PUSH8,
    PUSH9, PUSH10, PUSH11, PUSH12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private ExtraSmallPushType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PUSH0) ? "" : "col-xs-push-" + cssClassName.replace("push", "");
    }

}
