package de.agilecoders.wicket.core.markup.html.bootstrap.layout.pull;

/**
 * Pulls for (non-large) desktops.
 */
public enum MediumPullType implements PullType {

    PULL0,
    PULL1, PULL2, PULL3, PULL4,
    PULL5, PULL6, PULL7, PULL8,
    PULL9, PULL10, PULL11, PULL12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private MediumPullType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PULL0) ? "" : "col-md-pull-" + cssClassName.replace("pull", "");
    }

}
