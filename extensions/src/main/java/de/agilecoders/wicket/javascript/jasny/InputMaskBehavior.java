package de.agilecoders.wicket.javascript.jasny;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.string.Strings;

/**
 * A behavior that applies a mask on an &lt;input&gt; field by using
 * <a href="http://jasny.github.com/bootstrap/javascript.html#inputmask">Jasny InputMask</a>.
 *
 * <p>An input mask consists of a mask and placeholder.<br/>
 * The possible characters for the mask are:
 * <ul>
 *     <li>a - allows entering any alpha character ([a-zA-Z])</li>
 *     <li>9 - allows entering any number character ([0-9])</li>
 *     <li>? - allows entering any alpha or number character ([a-zA-Z0-9])</li>
 *     <li>* - allows entering any character (.)</li>
 * </ul>
 * </p>
 */
public abstract class InputMaskBehavior extends Behavior
{
	@Override
	public void bind(Component component)
	{
		super.bind(component);

		component.setOutputMarkupId(true);
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);

		String mask = getMask();
		if (!Strings.isEmpty(mask)) {
			response.render(JavaScriptHeaderItem.forReference(JasnyJsReference.INSTANCE));

			String placeholder = getPlaceholder();
			String placeholderOption = "";
			// Set the placeholder only if it is non-empty and not the default ('_')
			if (!(Strings.isEmpty(placeholder) || "_".equals(placeholder))) {
				placeholderOption = String.format(", placeholder: '%s'", placeholder);
			}

			response.render(OnDomReadyHeaderItem.forScript(
					String.format("$('#%s').inputmask({mask: '%s'%s})",
							component.getMarkupId(), mask, placeholderOption)));
		}
	}

	/**
	 * @return The mask to apply on the input field
	 */
	protected abstract String getMask();

	/**
	 * @return The placeholder to use as replacement for non-entered characters
	 */
	protected String getPlaceholder() {
		return "_";
	}
}
