package de.agilecoders.wicket.core.markup.html.bootstrap.layout.pull;

/**
 * Pulls for phones.
 */
public enum ExtraSmallPullType implements PullType {

    PULL0,
    PULL1, PULL2, PULL3, PULL4,
    PULL5, PULL6, PULL7, PULL8,
    PULL9, PULL10, PULL11, PULL12;

    private final String cssClassName;

    /**
     * Constructor.
     */
    private ExtraSmallPullType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(PULL0) ? "" : "col-xs-pull-" + cssClassName.replace("pull", "");
    }

}
