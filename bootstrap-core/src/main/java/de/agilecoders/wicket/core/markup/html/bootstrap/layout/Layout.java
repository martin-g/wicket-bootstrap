package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * Defines all possible layout types.
 *
 * @author miha
 */
public enum Layout implements ICssClassNameProvider {
    Fluid("container-fluid"),
    Fixed("container");

    private final String cssClassName;

    /**
     * Construct.
     *
     * @param cssClassName The css class name
     */
    Layout(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }
}
