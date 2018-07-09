package de.agilecoders.wicket.core.markup.html.bootstrap.layout.order;

/**
 * Order columns for extra small screens.
 *
 * @author Jan Ferko
 */
public enum ExtraSmallOrderType implements OrderType {
    ORDER0,
    ORDER1, ORDER2, ORDER3, ORDER4, ORDER5, ORDER6,
    ORDER7, ORDER8, ORDER9, ORDER10, ORDER11, ORDER12,
    FIRST, LAST;

    private final String cssClassName;

    private ExtraSmallOrderType() {
        this.cssClassName = name().toLowerCase();
    }

    @Override
    public String cssClassName() {
        return equals(ORDER0)
                ? ""
                : String.format("order-%s", cssClassName.replace("order", ""));
    }
}
