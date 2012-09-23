package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * A LabelType defines the type of label which changes highlighted color.
 *
 * @author miha
 * @version 1.0
 */
public enum LabelType implements CssClassNameProvider {
    Default, Success, Warning, Important, Info, Inverse;

    @Override
    public String cssClassName() {
        return equals(Default) ? "" : name().toLowerCase();
    }

    public String cssClassName(String prefix) {
        return equals(Default) ? "" : prefix + "-" + name().toLowerCase();
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }

    public CssClassNameAppender newCssClassNameModifier(String prefix) {
        return new CssClassNameAppender(cssClassName(prefix));
    }
}
