/**
 * 
 */
package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * User to render the radio options on {@link BootstrapRadioGroup}.
 * @author reiern70
 */
public interface IRadioChoiceRenderer<T> extends IDetachable {

	IModel<T> modelOf(T option);

	IModel<String> lableOf(T option);
	
	String getButtonClass(T option);
}
