package de.agilecoders.wicket.core.markup.html.bootstrap.helpers;

import java.util.Optional;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Adds Text and Background color utility classes to components.
 * Color and background helpers combine the power of our .text-* utilities and .bg-* utilities in one class.
 * <a href="https://getbootstrap.com/docs/5.3/helpers/color-background/></a>
 * <a href="https://getbootstrap.com/docs/5.3/components/badge/#background-colors"></a>
 *
 * @author Matthias Drummer
 */
public class TextAndBackgroundColorBehavior extends BootstrapBaseBehavior {

	/**
	 * Enumeration that contains all text and background color combinations provided by bootstrap.
	 *
	 * <cite>
	 * Set a background-color with contrasting foreground color with our .text-bg-{color} helpers. Previously it was required to manually pair your choice
	 * of .text-{color} and .bg-{color} utilities for styling, which you still may use if you prefer.
	 * </cite>
	 *
	 * @author Matthias Drummer
	 */
	public enum TextAndBackgroundColor implements ICssClassNameProvider {

		Primary("primary"),
		Secondary("secondary"),
		Success("success"),
		Info("info"),
		Warning("warning"),
		Danger("danger"),
		Light("light"),
		Dark("dark"),
		;

		private final String cssClassName;

		TextAndBackgroundColor(final String value) {
			this.cssClassName = "text-bg-" + value;
		}

		@Override
		public String cssClassName() {
			return cssClassName;
		}
	}

	private final IModel<TextAndBackgroundColor> textAndBackgroundColorModel;

	public TextAndBackgroundColorBehavior(final TextAndBackgroundColor textAndBackgroundColor) {
		this(Model.of(textAndBackgroundColor));
	}

	public TextAndBackgroundColorBehavior(final IModel<TextAndBackgroundColor> textAndBackgroundColorModel) {
		// The Model must have a proper value set
		Args.notNull(textAndBackgroundColorModel, "textAndBackgroundColorModel.getObject()");
		this.textAndBackgroundColorModel = textAndBackgroundColorModel;
	}

	@Override
	public void onComponentTag(final Component component, final ComponentTag tag) {
		super.onComponentTag(component, tag);
		Attributes.addClass(tag, textAndBackgroundColorModel.getObject());
	}

	/**
	 * Sets a {@link TextAndBackgroundColor} value.
	 *
	 * @param textAndBackgroundColor the text and background color value
	 * @return this for chaining
	 */
	public TextAndBackgroundColorBehavior setTextAndBackgroundColor(final TextAndBackgroundColor textAndBackgroundColor) {
		textAndBackgroundColorModel.setObject(textAndBackgroundColor);
		return this;
	}

	public IModel<TextAndBackgroundColor> getTextAndBackgroundColorModel() {
		return textAndBackgroundColorModel;
	}

	public Optional<TextAndBackgroundColor> getTextAndBackgroundColor() {
		return Optional.ofNullable(textAndBackgroundColorModel.getObject());
	}

	public static TextAndBackgroundColorBehavior primary() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Primary);
	}

	public static TextAndBackgroundColorBehavior secondary() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Secondary);
	}

	public static TextAndBackgroundColorBehavior success() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Success);
	}

	public static TextAndBackgroundColorBehavior info() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Info);
	}

	public static TextAndBackgroundColorBehavior warning() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Warning);
	}

	public static TextAndBackgroundColorBehavior danger() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Danger);
	}

	public static TextAndBackgroundColorBehavior light() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Light);
	}

	public static TextAndBackgroundColorBehavior dark() {
		return new TextAndBackgroundColorBehavior(TextAndBackgroundColor.Dark);
	}
}

