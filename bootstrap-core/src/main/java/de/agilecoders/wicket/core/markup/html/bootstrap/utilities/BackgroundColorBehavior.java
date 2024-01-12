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
 * Adds background color utility classes to components.
 *
 * @author Jan Ferko
 * @author Matthias Drummer
 */
public class BackgroundColorBehavior extends BootstrapBaseBehavior {

	/**
	 * Enumeration that contains all bootstrap background colors
	 * <a href="https://getbootstrap.com/docs/5.3/utilities/background/"></a>
	 */
	public enum BackgroundColor implements ICssClassNameProvider {

		Primary("primary"),
		Primary_Subtle("primary-subtle"),
		Secondary("secondary"),
		Secondary_Subtle("secondary-subtle"),
		Success("success"),
		Success_Subtle("success-subtle"),
		Info("info"),
		Info_Subtle("info-subtle"),
		Warning("warning"),
		Warning_Subtle("warning-subtle"),
		Danger("danger"),
		Danger_Subtle("danger-subtle"),
		Light("light"),
		Light_Subtle("light-subtle"),
		Dark("dark"),
		Dark_Subtle("dark-subtle"),
		Black("black"),
		White("white"),
		Body("body"),
		Transparent("transparent"),
		Body_Secondary("body-secondary"),
		Body_Tertiary("body-tertiary"),
		;

		private final String cssClassName;

		BackgroundColor(final String value) {
			this.cssClassName = "bg-" + value;
		}

		@Override
		public String cssClassName() {
			return cssClassName;
		}
	}

	/**
	 * Background color that should be added to component.
	 */
	private final IModel<BackgroundColor> backgroundColorModel;
	// The opacity model is per default empty
	private final IModel<BackgroundOpacity> backgroundOpacityModel = Model.of();
	private final IModel<Boolean> gradientModel = Model.of(Boolean.FALSE);

	/**
	 * Constructs new instance for given {@link BackgroundColor}.
	 *
	 * @param backgroundColor the background color that should be added to component.
	 */
	public BackgroundColorBehavior(final BackgroundColor backgroundColor) {
		this(Model.of(backgroundColor));
	}

	/**
	 * Construct new instacne for given backgroundColor model.
	 *
	 * @param backgroundColorModel the background color model containing the {@link BackgroundColor} that should be added to the component
	 */
	public BackgroundColorBehavior(final IModel<BackgroundColor> backgroundColorModel) {
		this.backgroundColorModel = backgroundColorModel;
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		Attributes.addClass(tag, backgroundColorModel.getObject());
		if (Objects.nonNull(backgroundOpacityModel)) {
			Attributes.addClass(tag, backgroundOpacityModel.getObject());
		}
		if (gradientModel.getObject()) {
			Attributes.addClass(tag, "bg-gradient");
		}
	}

	/**
	 * Sets the backgroundColor.
	 *
	 * @param backgroundColor the {@link BackgroundColor}
	 * @return this for chaining
	 */
	public BackgroundColorBehavior withBackgroundColor(BackgroundColor backgroundColor) {
		backgroundColorModel.setObject(backgroundColor);
		return this;
	}

	/**
	 * Sets the background opacity.
	 *
	 * @param backgroundOpacity the {@link BackgroundOpacity}
	 * @return this for chaining
	 */
	public BackgroundColorBehavior withOpacity(final BackgroundOpacity backgroundOpacity) {
		backgroundOpacityModel.setObject(backgroundOpacity);
		return this;
	}

	/**
	 * Sets if the Background should show some gradient
	 *
	 * @param withGradient true if a gradient has to be shown
	 * @return this for chaining
	 */
	public BackgroundColorBehavior withGradient(final boolean withGradient) {
		gradientModel.setObject(withGradient);
		return this;
	}

	/**
	 * Returns the {@link BackgroundColor} which is currently set
	 *
	 * @return the backgroundColor
	 */
	public BackgroundColor getBackgroundColor() {
		return backgroundColorModel.getObject();
	}

	/**
	 * Returns the model of the {@link BackgroundColor}.
	 *
	 * @return the backgroundColor model
	 */
	public IModel<BackgroundColor> getBackgroundColorModel() {
		return backgroundColorModel;
	}

	/**
	 * Returns the {@link BackgroundOpacity} which is currently set. Optional because it may not be set.
	 *
	 * @return the {@link BackgroundOpacity} as optional
	 */
	public Optional<BackgroundOpacity> getBackgroundOpacity() {
		return Optional.ofNullable(backgroundOpacityModel.getObject());
	}

	/**
	 * Returns the {@link BackgroundOpacity} model
	 *
	 * @return the background opacity model
	 */
	public IModel<BackgroundOpacity> getBackgroundOpacityModel() {
		return backgroundOpacityModel;
	}

	/**
	 * Returns if the background has some gradient
	 *
	 * @return the gradient model value
	 */
	public boolean hasGradient() {
		return gradientModel.getObject();
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Primary} to component
	 *
	 * @return behavior that adds primary background color to component
	 */
	public static BackgroundColorBehavior primary() {
		return new BackgroundColorBehavior(BackgroundColor.Primary);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Primary_Subtle} to component
	 *
	 * @return behavior that adds primary subtle background color to component
	 */
	public static BackgroundColorBehavior primarySubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Primary_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Secondary} to component
	 *
	 * @return behavior that adds secondary background color to component
	 */
	public static BackgroundColorBehavior secondary() {
		return new BackgroundColorBehavior(BackgroundColor.Secondary);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Secondary_Subtle} to component
	 *
	 * @return behavior that adds secondary subtle background color to component
	 */
	public static BackgroundColorBehavior secondarySubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Secondary_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Success} to component
	 *
	 * @return behavior that adds success background color to component
	 */
	public static BackgroundColorBehavior success() {
		return new BackgroundColorBehavior(BackgroundColor.Success);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Success_Subtle} to component
	 *
	 * @return behavior that adds success subtle background color to component
	 */
	public static BackgroundColorBehavior successSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Success_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Danger} to component
	 *
	 * @return behavior that adds danger background color to component
	 */
	public static BackgroundColorBehavior danger() {
		return new BackgroundColorBehavior(BackgroundColor.Danger);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Danger_Subtle} to component
	 *
	 * @return behavior that adds danger subtle background color to component
	 */
	public static BackgroundColorBehavior dangerSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Danger_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Warning} to component
	 *
	 * @return behavior that adds warning background color to component
	 */
	public static BackgroundColorBehavior warning() {
		return new BackgroundColorBehavior(BackgroundColor.Warning);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Warning_Subtle} to component
	 *
	 * @return behavior that adds warning subtle background color to component
	 */
	public static BackgroundColorBehavior warningSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Warning_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Info} to component
	 *
	 * @return behavior that adds info background color to component
	 */
	public static BackgroundColorBehavior info() {
		return new BackgroundColorBehavior(BackgroundColor.Info);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Info_Subtle} to component
	 *
	 * @return behavior that adds info subtle background color to component
	 */
	public static BackgroundColorBehavior infoSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Info_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Light} to component
	 *
	 * @return behavior that adds light background color to component
	 */
	public static BackgroundColorBehavior light() {
		return new BackgroundColorBehavior(BackgroundColor.Light);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Light_Subtle} to component
	 *
	 * @return behavior that adds light subtle background color to component
	 */
	public static BackgroundColorBehavior lightSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Light_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Dark} to component
	 *
	 * @return behavior that adds dark background color to component
	 */
	public static BackgroundColorBehavior dark() {
		return new BackgroundColorBehavior(BackgroundColor.Dark);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Dark_Subtle} to component
	 *
	 * @return behavior that adds dark subtle background color to component
	 */
	public static BackgroundColorBehavior darkSubtle() {
		return new BackgroundColorBehavior(BackgroundColor.Dark_Subtle);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Body_Secondary} to component
	 *
	 * @return behavior that adds body secondary background color to component
	 */
	public static BackgroundColorBehavior bodySecondary() {
		return new BackgroundColorBehavior(BackgroundColor.Body_Secondary);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Body_Tertiary} to component
	 *
	 * @return behavior that adds body tertiary background color to component
	 */
	public static BackgroundColorBehavior bodyTertiary() {
		return new BackgroundColorBehavior(BackgroundColor.Body_Tertiary);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Body} to component
	 *
	 * @return behavior that adds body background color to component
	 */
	public static BackgroundColorBehavior body() {
		return new BackgroundColorBehavior(BackgroundColor.Body);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Black} to component
	 *
	 * @return behavior that adds black background color to component
	 */
	public static BackgroundColorBehavior black() {
		return new BackgroundColorBehavior(BackgroundColor.Black);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#White} to component
	 *
	 * @return behavior that adds white background color to component
	 */
	public static BackgroundColorBehavior white() {
		return new BackgroundColorBehavior(BackgroundColor.White);
	}

	/**
	 * Constructs new behavior that adds {@link BackgroundColor#Transparent} to component
	 *
	 * @return behavior that adds transparent background color to component
	 */
	public static BackgroundColorBehavior transparent() {
		return new BackgroundColorBehavior(BackgroundColor.Transparent);
	}
}
