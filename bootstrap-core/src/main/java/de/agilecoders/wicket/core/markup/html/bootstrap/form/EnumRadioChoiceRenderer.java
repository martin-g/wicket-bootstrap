/**
 * 
 */
package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Classes;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

/**
 * Enum radio renderer similar to {@link EnumChoiceRenderer}. Option labels are pulled 
 * from the resources. 
 * 
 * @author reiern70
 */
public class EnumRadioChoiceRenderer<T extends Enum<T>> extends DefaultRadioChoiceRenderer<T> {
	
	/**
	 * component used to resolve i18n resources for this renderer.
	 */
	private final Component resourceSource;
	
	
	public EnumRadioChoiceRenderer(Type type) {
		this(type, null);
	}
	
	public EnumRadioChoiceRenderer(Type type, Component resourceSource) {
		super(type, null);
		this.resourceSource = resourceSource;
	}
	
	
	public IModel<String> lableOf(T option) {
		return Model.of(getDisplayValue(option).toString());
	}

	/** {@inheritDoc} */
	public final CharSequence getDisplayValue(T object)
	{
		final String value;

		String key = resourceKey(object);

		if (resourceSource != null)
		{
			value = resourceSource.getString(key);
		}
		else
		{
			value = Application.get().getResourceSettings().getLocalizer().getString(key, null);
		}

		return postprocess(value);
	}
	
	/**
	 * Postprocesses the {@code value} after it is retrieved from the localizer. Default
	 * implementation escapes any markup found in the {@code value}.
	 * 
	 * @param value
	 * @return postprocessed value
	 */
	protected CharSequence postprocess(String value)
	{
		return value;
	}

	/**
	 * Translates the {@code object} into resource key that will be used to lookup the value shown
	 * to the user
	 * 
	 * @param object
	 * @return resource key
	 */
	protected String resourceKey(T object)
	{
		return Classes.simpleName(object.getDeclaringClass()) + '.' + object.name();
	}

}
