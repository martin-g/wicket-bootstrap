package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * Ajax version of  BootstrapRadioGroup
 */
public abstract class AjaxBootstrapRadioGroup<T extends Serializable> extends BootstrapRadioGroup<T> {

    public AjaxBootstrapRadioGroup(String id, Collection<T> options) {
        super(id, new ArrayList<T>(options), null);
        initAjax();
    }

    public AjaxBootstrapRadioGroup(String id, Collection<T> options, IRadioChoiceRenderer<T> choiceRenderer) {
        super(id, new ArrayList<T>(options), choiceRenderer);
        initAjax();
    }

    public AjaxBootstrapRadioGroup(String id, IModel<T> model, Collection<T> options, IRadioChoiceRenderer<T> choiceRenderer) {
        super(id, model, new ArrayList<T>(options), choiceRenderer);
        initAjax();
    }

    private void initAjax() {
        setOutputMarkupId(true);
        setChangeHandler(new ISelectionChangeHandler<T>() {
            @Override
            public void onSelectionChanged(AjaxRequestTarget target, T bean) {
                target.add(AjaxBootstrapRadioGroup.this);
                AjaxBootstrapRadioGroup.this.onSelectionChanged(target, bean);
            }
        });
    }

    protected abstract void onSelectionChanged(AjaxRequestTarget target, T value);
}
