package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * Make a set of buttons appear vertically or horizontally stacked.
 */
public enum Orientation implements ICssClassNameProvider {
    Horizontal, Vertical;

    @Override
    public String cssClassName() {
        return equals(Horizontal) ? "" : "btn-group-" + name().toLowerCase();
    }

}