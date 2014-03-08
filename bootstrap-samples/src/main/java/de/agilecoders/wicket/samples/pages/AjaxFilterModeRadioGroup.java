package de.agilecoders.wicket.samples.pages;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.EnumRadioChoiceRenderer;

public abstract class AjaxFilterModeRadioGroup extends BootstrapRadioGroup<FilterMode> {

	public AjaxFilterModeRadioGroup(String id, final IModel<FilterMode> model) {
		super(id, model,  Arrays.asList(FilterMode.values()), new EnumRadioChoiceRenderer<FilterMode>(Type.Primary, null));
	
		setChangeHandler(new ISelectionChangeHandler<FilterMode>() {

			@Override
			public void onSelectionChanged(AjaxRequestTarget target,
					FilterMode bean) {
				onModeChanged(target, bean);
			}			
		});
	}

	protected abstract void onModeChanged(AjaxRequestTarget target, FilterMode filterMode);
}
