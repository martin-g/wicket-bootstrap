package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import java.util.Objects;
import java.util.Optional;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Adds text color utility classes to components.
 * <p>
 * See: https://getbootstrap.com/docs/5.3/utilities/colors/
 *
 * @author Jan Ferko
 */
public class TextColorBehavior extends BootstrapBaseBehavior {

	/**
	 * Enumeration that contains all Bootstrap TextColors.
	 * <a href="https://getbootstrap.com/docs/5.3/utilities/colors/"></a>
	 *
	 * @author Jan Ferko
	 */
	public enum TextColor implements ICssClassNameProvider {

		Primary("primary"),
		Primary_Emphasis("primary-emphasis"),
		Secondary("secondary"),
		Secondary_Emphasis("secondary-emphasis"),
		Success("success"),
		Success_Emphasis("success-emphasis"),
		Danger("danger"),
		Danger_Emphasis("danger-emphasis"),
		Warning("warning"),
		Warning_Emphasis("warning-emphasis"),
		Info("info"),
		Info_Emphasis("info-emphasis"),
		Light("light"),
		Light_Emphasis("light-emphasis"),
		Dark("dark"),
		Dark_Emphasis("dark-emphasis"),
		Body("body"),
		Body_Emphasis("body-emphasis"),
		Body_Secondary("body-secondary"),
		Body_Tertiary("body-tertiary"),
		White("white"),
		Black("black"),
		@Deprecated(forRemoval = true) // https://getbootstrap.com/docs/5.3/utilities/colors/ Documentation says it is deprecated and will be removed with BS-6.0.0
		Black50("black-50"),
		@Deprecated(forRemoval = true) // https://getbootstrap.com/docs/5.3/utilities/colors/ Documentation says it is deprecated and will be removed with BS-6.0.0
		White50("white-50"),
		;

		private final String cssClassName;

		/**
		 * Css class associated with this color.
		 *
		 * @return Css class associated with this color.
		 */
		TextColor(final String value) {
			this.cssClassName = "text-" + value;
		}

		@Override
		public String cssClassName() {
			return cssClassName;
		}
	}

	/**
	 * Enumeration of available text opacities.
	 * <a href="https://getbootstrap.com/docs/5.3/utilities/colors/#opacity"></a>
	 *
	 * @author Matthias Drummer
	 */
	public enum TextOpacity implements ICssClassNameProvider {

		Opacity_25("opacity-25"),
		Opacity_50("opacity-50"),
		Opacity_75("opacity-75"),
		Opacity_100("opacity-100"),
		;

		private final String cssClassName;

		TextOpacity(final String value) {
			this.cssClassName = "text-" + value;
		}

		@Override
		public String cssClassName() {
			return cssClassName;
		}
	}

	/**
	 * Color that should be added to component.
	 */
	private final IModel<TextColor> textColorModel;

	/**
	 * If any opacity should be added to the text.
	 */
	private final IModel<TextOpacity> textOpacityModel = Model.of();

	/**
	 * Constructs new instance for given color.
	 *
	 * @param color the color that should be added to component.
	 */
	public TextColorBehavior(final TextColor color) {
		this(Model.of(color));
	}

	/**
	 * @param colorModel
	 */
	public TextColorBehavior(final IModel<TextColor> colorModel) {
		this.textColorModel = colorModel;
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);

		Attributes.addClass(tag, textColorModel.getObject());
		if (Objects.nonNull(textOpacityModel.getObject())) {
			Attributes.addClass(tag, textOpacityModel.getObject());
		}
	}

	/**
	 * Sets color.
	 *
	 * @param color
	 * @return this for chaining
	 */
	public TextColorBehavior withTextColor(final TextColor color) {
		textColorModel.setObject(color);
		return this;
	}

	/**
	 * @return color
	 */
	public TextColor getTextColor() {
		return textColorModel.getObject();
	}

	/**
	 * @return color model
	 */
	public IModel<TextColor> getTextColorModel() {
		return textColorModel;
	}

	/**
	 * Sets opacity to the text. Null will disable opacity.
	 *
	 * @param textOpacity the {@link TextOpacity}
	 * @return this for chaining
	 */
	public TextColorBehavior withTextOpacity(final TextOpacity textOpacity) {
		this.textOpacityModel.setObject(textOpacity);
		return this;
	}

	/**
	 * Returns the {@link TextOpacity} which is set. Can be empty.
	 *
	 * @return the text opacity
	 */
	public Optional<TextOpacity> getTextOpacity() {
		return Optional.ofNullable(textOpacityModel.getObject());
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Primary} to component
	 *
	 * @return behavior that adds primary color to component
	 */
	public static TextColorBehavior primary() {
		return new TextColorBehavior(TextColor.Primary);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Primary_Emphasis} to component
	 *
	 * @return behavior that adds primary emphasis color to component
	 */
	public static TextColorBehavior primaryEmphasis() {
		return new TextColorBehavior(TextColor.Primary_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Secondary} to component
	 *
	 * @return behavior that adds secondary color to component
	 */
	public static TextColorBehavior secondary() {
		return new TextColorBehavior(TextColor.Secondary);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Secondary_Emphasis} to component
	 *
	 * @return behavior that adds secondary emphasis color to component
	 */
	public static TextColorBehavior secondaryEmphasis() {
		return new TextColorBehavior(TextColor.Secondary_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Success} to component
	 *
	 * @return behavior that adds success color to component
	 */
	public static TextColorBehavior success() {
		return new TextColorBehavior(TextColor.Success);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Success_Emphasis} to component
	 *
	 * @return behavior that adds success emphasis color to component
	 */
	public static TextColorBehavior successEmphasis() {
		return new TextColorBehavior(TextColor.Success_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Danger} to component
	 *
	 * @return behavior that adds danger color to component
	 */
	public static TextColorBehavior danger() {
		return new TextColorBehavior(TextColor.Danger);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Danger_Emphasis} to component
	 *
	 * @return behavior that adds danger emphasis color to component
	 */
	public static TextColorBehavior dangerEmphasis() {
		return new TextColorBehavior(TextColor.Danger_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Warning} to component
	 *
	 * @return behavior that adds warning color to component
	 */
	public static TextColorBehavior warning() {
		return new TextColorBehavior(TextColor.Warning);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Warning_Emphasis} to component
	 *
	 * @return behavior that adds warning emphasis color to component
	 */
	public static TextColorBehavior warningEmphasis() {
		return new TextColorBehavior(TextColor.Warning_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Info} to component
	 *
	 * @return behavior that adds info color to component
	 */
	public static TextColorBehavior info() {
		return new TextColorBehavior(TextColor.Info);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Info_Emphasis} to component
	 *
	 * @return behavior that adds info emphasis color to component
	 */
	public static TextColorBehavior infoEmphasis() {
		return new TextColorBehavior(TextColor.Info_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Light} to component
	 *
	 * @return behavior that adds light color to component
	 */
	public static TextColorBehavior light() {
		return new TextColorBehavior(TextColor.Light);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Light_Emphasis} to component
	 *
	 * @return behavior that adds light emphasis color to component
	 */
	public static TextColorBehavior lightEmphasis() {
		return new TextColorBehavior(TextColor.Light_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Dark} to component
	 *
	 * @return behavior that adds dark color to component
	 */
	public static TextColorBehavior dark() {
		return new TextColorBehavior(TextColor.Dark);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Dark_Emphasis} to component
	 *
	 * @return behavior that adds dark emphasis color to component
	 */
	public static TextColorBehavior darkEmphasis() {
		return new TextColorBehavior(TextColor.Dark_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Body} to component
	 *
	 * @return behavior that adds body color to component
	 */
	public static TextColorBehavior body() {
		return new TextColorBehavior(TextColor.Body);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Body_Emphasis} to component
	 *
	 * @return behavior that adds body emphasis color to component
	 */
	public static TextColorBehavior bodyEmphasis() {
		return new TextColorBehavior(TextColor.Body_Emphasis);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Body_Secondary} to component
	 *
	 * @return behavior that adds body secondary color to component
	 */
	public static TextColorBehavior bodySecondary() {
		return new TextColorBehavior(TextColor.Body_Secondary);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Body_Tertiary} to component
	 *
	 * @return behavior that adds body tertiary color to component
	 */
	public static TextColorBehavior bodyTertiary() {
		return new TextColorBehavior(TextColor.Body_Tertiary);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#White} to component
	 *
	 * @return behavior that adds white color to component
	 */
	public static TextColorBehavior white() {
		return new TextColorBehavior(TextColor.White);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Black} to component
	 *
	 * @return behavior that adds black color to component
	 */
	public static TextColorBehavior black() {
		return new TextColorBehavior(TextColor.Black);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#Black50} to component
	 *
	 * @return behavior that adds black-50 color to component
	 */
	public static TextColorBehavior black50() {
		return new TextColorBehavior(TextColor.Black50);
	}

	/**
	 * Constructs new behavior that adds {@link TextColor#White50} to component
	 *
	 * @return behavior that adds white-50 color to component
	 */
	public static TextColorBehavior white50() {
		return new TextColorBehavior(TextColor.White50);
	}
}
