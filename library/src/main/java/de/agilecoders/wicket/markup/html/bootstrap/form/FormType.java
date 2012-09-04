package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * TODO document
 */
public enum FormType implements CssClassNameProvider {
    Default(""), // Stacked, left-aligned labels on top of form controls
    Inline("form-inline"), // Left-aligned label and inline-block controls for compact style
    Search("form-search"), // Extra-rounded text input for a typical search aesthetic
    Horizontal("form-horizontal"); // (default) Float left, right-aligned labels on same line as controls

    private final String cssClassName;

    private FormType(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender(this);
    }
}