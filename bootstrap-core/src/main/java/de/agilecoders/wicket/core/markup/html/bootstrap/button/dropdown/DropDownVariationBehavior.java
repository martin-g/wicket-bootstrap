package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
* Behavior for DropDownButton and Splitbutton to enable "dropping"
* to the right, left and up
*
* @author helmut
**/

public class DropDownVariationBehavior extends BootstrapBaseBehavior {
	private static final long serialVersionUID = 1L;

	/**
	 * Variant for DropdownButton defines if "drop" is done up, right, left or
	 * default down
	 */
	public enum Variation implements ICssClassNameProvider {
		DROPRIGHT("dropright"), DROPLEFT("dropleft"), DROPUP("dropup"), DROPDOWN("");
		private String className;

		/**
		 * Construct.
		 *
		 * @param className
		 *            the css class name
		 */
		Variation(final String className) {
			this.className = className;
		}

		@Override
		public String cssClassName() {
			return className;
		}
	}

	private final IModel<Variation> variation;

	/**
	 * Construct.
	 *
	 * @param variation
	 *            the alignment to use
	 */
	public DropDownVariationBehavior(final IModel<Variation> variation) {
		this.variation = Args.notNull(variation, "variation");
	}

	/**
	 * Construct.
	 *
	 * @param variation
	 *            the alignment to use
	 */
	public DropDownVariationBehavior(final Variation variation) {
		this(Model.of(Args.notNull(variation, "variation")));
	}

	@Override
	public void onComponentTag(final Component component, final ComponentTag tag) {
		super.onComponentTag(component, tag);

		// remove existing alignment class names to allow switching alignment during
		// ajax updates
		Attributes.removeClass(tag, Variation.DROPLEFT.cssClassName(), Variation.DROPRIGHT.cssClassName(),
				Variation.DROPUP.cssClassName());

		Variation value = variation.getObject();
		switch (value) {
		case DROPLEFT:
		case DROPRIGHT:
		case DROPUP:
			Attributes.addClass(tag, value.cssClassName());
		default:
			break;
		}
	}

	public DropDownVariationBehavior setVariation(final Variation variation) {
		this.variation.setObject(variation);
		return this;
	}

	@Override
	public void detach(Component component) {
		super.detach(component);
		variation.detach();
	}
}
