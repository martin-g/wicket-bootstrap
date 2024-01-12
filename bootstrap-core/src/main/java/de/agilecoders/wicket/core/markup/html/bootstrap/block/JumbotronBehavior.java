package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior.TextAndBackgroundColor;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.core.util.CssClassNames.Builder;

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
public class JumbotronBehavior extends Behavior {

	private TextAndBackgroundColor textAndBackgroundColor;

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);

		final Builder classNameBuilder = CssClassNames.newBuilder().add("p-5", "rounded-3");
		if (Objects.nonNull(textAndBackgroundColor)) {
			classNameBuilder.add(textAndBackgroundColor.cssClassName());
		}
		Attributes.addClass(tag, classNameBuilder.asString());
		Components.assertTag(component, tag, "div");
	}

	public void setTextAndBackgroundColor(final TextAndBackgroundColor textAndBackgroundColor) {
		this.textAndBackgroundColor = textAndBackgroundColor;
	}

	@Override
	public void bind(final Component component) {
		super.bind(component);
		BootstrapBaseBehavior.addTo(component);
	}

	@Override
	public void unbind(final Component component) {
		super.unbind(component);
		BootstrapBaseBehavior.removeFrom(component);
	}

}
