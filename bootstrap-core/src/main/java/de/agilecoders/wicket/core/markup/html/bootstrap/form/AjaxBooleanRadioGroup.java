package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import java.util.Arrays;

public abstract class AjaxBooleanRadioGroup extends BootstrapRadioGroup<Boolean> {

	public AjaxBooleanRadioGroup(String id, final IModel<Boolean> model) {
		super(id, model,  Arrays.asList(Boolean.TRUE, Boolean.FALSE), new BooleanRadioChoiceRenderer(Type.Primary, null));
	    setOutputMarkupId(true);
		setChangeHandler(new ISelectionChangeHandler<Boolean>() {
			@Override
			public void onSelectionChanged(AjaxRequestTarget target, Boolean bean) {
                model.setObject(bean);
                target.add(AjaxBooleanRadioGroup.this);
				AjaxBooleanRadioGroup.this.onSelectionChanged(target, bean);
			}			
		});
	}

	protected abstract void onSelectionChanged(AjaxRequestTarget target, Boolean value);
}
