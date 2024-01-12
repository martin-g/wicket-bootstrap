package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior.TextAndBackgroundColor;

/**
 * #### Description
 * <p>
 * A lightweight, flexible component that can optionally extend the entire viewport to showcase key content on your site.
 * <p>
 * Jumbotron was removed with Bootstrap 5.0.0.
 * documentation: https://getbootstrap.com/docs/5.3/migration/#jumbotron
 * <p>
 * #### Usage
 * <p>
 * ```java
 * component.add(new HeroBehavior());
 * ```
 * <p>
 * ```html
 * <div wicket:id="componentId">
 * <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 * <small>Someone famous</small>
 * </div>
 * ```
 * <p>
 * To make the jumbotron full width, and without rounded corners, place it outside all `.containers` and instead add a
 * `.container` within.
 * <p>
 * ```html
 * <div wicket:id="componentId">
 *      <div class="container">
 *          content
 *      </div>
 * </div>
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
@Deprecated // removed since BS 5.0.0
public class Jumbotron extends WebMarkupContainer {

	private final JumbotronBehavior jumbotronBehavior;

	/**
	 * Constructor.
	 *
	 * @param componentId The non-null id of a new component
	 */
	public Jumbotron(final String componentId) {
		this(componentId, null);
	}

	/**
	 * Constructor.
	 *
	 * @param componentId The non-null id of a new component
	 * @param model the component's model
	 */
	public Jumbotron(final String componentId, final IModel<?> model) {
		super(componentId, model);
		add(jumbotronBehavior = new JumbotronBehavior());
	}

	/**
	 * Sets the TextAndBackground Color.
	 *
	 * @param textAndBackgroundColor the {@link TextAndBackgroundColor}
	 * @return this
	 */
	public Jumbotron withBackgroundColor(final TextAndBackgroundColor textAndBackgroundColor) {
		jumbotronBehavior.setTextAndBackgroundColor(textAndBackgroundColor);
		return this;
	}
}



