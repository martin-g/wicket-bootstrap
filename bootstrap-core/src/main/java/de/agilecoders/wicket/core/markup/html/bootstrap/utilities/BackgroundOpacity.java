package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * Enumeration that provides opacity css classes for backgrounds
 * <a href="https://getbootstrap.com/docs/5.3/utilities/background/#opacity"></a>
 *
 * @author Matthias Drummer
 */
public enum BackgroundOpacity implements ICssClassNameProvider {

    Opacity_10("opacity-10"),
    Opacity_25("opacity-25"),
    Opacity_50("opacity-50"),
    Opacity_75("opacity-75"),
    Opacity_100("opacity-100"),
    ;

    private final String cssClassName;

    BackgroundOpacity(final String value) {
        this.cssClassName = "bg-" + value;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }
}
