package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import java.util.Arrays;

import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public class BooleanRadioGroup extends BootstrapRadioGroup<Boolean> {

	public BooleanRadioGroup(String id, final IModel<Boolean> model) {
		super(id, model,  Arrays.asList(Boolean.TRUE, Boolean.FALSE));
        setChoiceRenderer(new BooleanRadioChoiceRenderer(Type.Primary, this));
	    setOutputMarkupId(true);
	}
}
