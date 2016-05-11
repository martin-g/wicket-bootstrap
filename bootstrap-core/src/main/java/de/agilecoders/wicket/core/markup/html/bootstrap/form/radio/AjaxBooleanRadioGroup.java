package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

public abstract class AjaxBooleanRadioGroup extends BootstrapRadioGroup<Boolean> {

    public AjaxBooleanRadioGroup(String id, final IModel<Boolean> model) {
        this(id, model, Type.Danger);
    }

    public AjaxBooleanRadioGroup(String id, final IModel<Boolean> model, Type type) {
        super(id, model,  Arrays.asList(Boolean.TRUE, Boolean.FALSE));
        setChoiceRenderer(new BooleanRadioChoiceRenderer(type, this));
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
