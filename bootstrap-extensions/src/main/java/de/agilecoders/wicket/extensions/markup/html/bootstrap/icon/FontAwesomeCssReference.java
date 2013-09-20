package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * reference for font awesome css
 * 
 */
public class FontAwesomeCssReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton instance of this reference
	 */
	private static final FontAwesomeCssReference INSTANCE = new FontAwesomeCssReference();

	/**
	 * @return the single instance of the resource reference
	 */
	public static FontAwesomeCssReference instance() {
		return INSTANCE;
	}

	/**
	 * Private constructor.
	 */
	private FontAwesomeCssReference() {
		super(FontAwesomeCssReference.class, "css/font-awesome.css");
	}
}
