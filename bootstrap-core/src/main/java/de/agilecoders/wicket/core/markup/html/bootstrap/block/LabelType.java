package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * #### Description
 *
 * A LabelType defines the type of label which changes highlighted color.
 *
 * documentation: http://getbootstrap.com/components/#labels
 */
public enum LabelType implements ICssClassNameProvider, ICssClassNameModifier {
    Default,
    Primary,
    Success,
    Warning,
    Info,
    Danger;

    @Override
    public String cssClassName() {
        return name().toLowerCase();
    }

    public String cssClassName(final String prefix) {
        return prefix + "-" + name().toLowerCase();
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }

    public CssClassNameAppender newCssClassNameModifier(final String prefix) {
        return new CssClassNameAppender(cssClassName(prefix));
    }
}
