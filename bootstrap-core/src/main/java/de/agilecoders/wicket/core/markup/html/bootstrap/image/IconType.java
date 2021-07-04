package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * References all available icons inside the icon sprite.
 */
public abstract class IconType implements ICssClassNameProvider, ICssClassNameModifier {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /** CSS classname. */
    private final String cssClassName;
    /** The body of the tag (for material icons &lt;i class="material-icon"&gt;tag-body&lt;/i&gt;). */
    private final String tagBody;

    /**
     * Constructor.
     *
     * @param cssClassName The css class name of the icon reference
     */
    protected IconType(String cssClassName) {
        Args.notEmpty(cssClassName, "cssClassName");
        this.cssClassName = cssClassName.toLowerCase();
        tagBody = null;
    }

    /**
     * Constructor.
     *
     * @param cssClassName The css class name of the icon reference
     * @param tagBody the body of the tag (for material icons &lt;i class="material-icons"&gt;tag-content&lt;/i&gt;)
     */
    protected IconType(String cssClassName, String tagBody) {
        Args.notEmpty(cssClassName, "cssClassName");
        Args.notEmpty(tagBody, "tagContent");
        this.cssClassName = cssClassName.toLowerCase();
        this.tagBody = tagBody.toLowerCase();
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

    /**
     * @return the tag content
     */
    public String getTagBody() {
        return tagBody;
    }
}

