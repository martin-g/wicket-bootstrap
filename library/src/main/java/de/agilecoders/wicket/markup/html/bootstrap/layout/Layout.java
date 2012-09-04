package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public enum Layout implements CssClassNameProvider {
    Fluid("container-fluid"),
    Fixed("container");

    private final String cssClassName;

    Layout(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender(cssClassName());
    }
}
