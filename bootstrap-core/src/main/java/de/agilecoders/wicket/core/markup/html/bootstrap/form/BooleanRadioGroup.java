package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import org.apache.wicket.model.IModel;

import java.util.Arrays;

public class BooleanRadioGroup extends BootstrapRadioGroup<Boolean> {

	public BooleanRadioGroup(String id, final IModel<Boolean> model) {
		super(id, model,  Arrays.asList(Boolean.TRUE, Boolean.FALSE), new BooleanRadioChoiceRenderer(Type.Primary, null));
	    setOutputMarkupId(true);
	}
}
