package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

import org.apache.wicket.util.lang.Args;

/**
 * References all available icons inside the icon sprite.
 *
 * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
 */
public abstract class IconType implements ICssClassNameProvider, ICssClassNameModifier {
    /** CSS classname. */
    private String cssClassName;

    /**
     * Constructor.
     *
     * @param cssClassName The css class name of the icon reference
     */
    protected IconType(String cssClassName) {
        Args.notEmpty(cssClassName, "cssClassName");
        this.cssClassName = cssClassName.toLowerCase();
    }

	/**
	 * @return the cssClassName
	 */
	protected String getCssClassName() {
		return cssClassName;
	}
    
    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(getCssClassName());
    }
}
