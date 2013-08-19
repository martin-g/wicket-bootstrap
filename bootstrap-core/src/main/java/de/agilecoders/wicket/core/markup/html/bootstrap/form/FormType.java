package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * Defines all possible form types
 */
public enum FormType implements ICssClassNameProvider, ICssClassNameModifier {
    Default(""), // Stacked, left-aligned labels on top of form controls
    Inline("form-inline"), // Left-aligned label and inline-block controls for compact style
    Horizontal("form-horizontal"); // (default) Float left, right-aligned labels on same line as controls

    private final String cssClassName;

    /**
     * Construct.
     *
     * @param cssClassName The css class name to use
     */
    private FormType(final String cssClassName) {
        this.cssClassName = cssClassName;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(this);
    }
}
