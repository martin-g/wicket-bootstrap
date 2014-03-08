/**
 * 
 */
package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * Allows to quickly build radio options as in
 * 
 * http://getbootstrap.com/javascript/#buttons
 * 
 * 
 * @author reiern70
 *
 */
public class BootstrapRadioGroup<T> extends GenericPanel<T> {

	private  List<IModel<T>> options;
	
	private IRadioChoiceRenderer<T> choiceRenderer;
	
	private RadioGroup<T> radioGroup;
	
	/**
	 * Callback to be added to receive AJAX notification when selection change.
	 * 
	 * @author reiern70
	 *
	 * @param <T>
	 */
	public static interface ISelectionChangeHandler<T>  extends Serializable {
		
		void onSelectionChanged(AjaxRequestTarget target, T bean);
	}
	
	/**
	 * If set an AjaxFormChoiceComponentUpdatingBehavior will be added to inner
	 * {@link RadioGroup} and the interface will be notified about changes on 
	 * selected radio on client.
	 * 
	 */
	private ISelectionChangeHandler<T> changeHandler;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public BootstrapRadioGroup(String id,  List<T> options, IRadioChoiceRenderer<T> choiceRenderer) {
		this(id, null, options, choiceRenderer);
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param model
	 */
	public BootstrapRadioGroup(String id, IModel<T> model, List<T> options, IRadioChoiceRenderer<T> choiceRenderer) {
		super(id, model);		
		Args.notNull(choiceRenderer, "choiceRenderer");
		this.choiceRenderer = choiceRenderer;
		Args.notEmpty(options, "options");
		this.options = new ArrayList<IModel<T>>();
		for(T t: options) {
			this.options.add(choiceRenderer.modelOf(t));
		}
	}
	
	@Override
	protected void detachModel() {
		super.detachModel();
		for(IModel<T> model: options) {
			model.detach();
		}
		choiceRenderer.detach();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();		
		radioGroup = new RadioGroup<T>("radioGroup", getModel());
		tuneRadioGroup(radioGroup);
		radioGroup.setRenderBodyOnly(false);
		radioGroup.setOutputMarkupId(true);
		add(radioGroup);
		if(changeHandler != null) {
			radioGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					changeHandler.onSelectionChanged(target, radioGroup.getModel().getObject());
				}
			});
		}
 		RepeatingView radios = new RepeatingView("radios");
		radioGroup.add(radios);
		for(final IModel<T> model: options) {
			WebMarkupContainer wm = new WebMarkupContainer(radios.newChildId()) {
				@Override
				protected void onComponentTag(ComponentTag tag) {
					super.onComponentTag(tag);
					// TODO: use model comparator?
					boolean active = model.getObject().equals(BootstrapRadioGroup.this.getModel().getObject());
					tag.put("class", "btn "+ choiceRenderer.getButtonClass(model.getObject())+ (active?" active":""));
				}
			};
			radios.add(wm);
			Radio<T> radio = new Radio<T>("radio", model, radioGroup);
			tuneRadio(radio);
			wm.add(radio);
			wm.add(new Label("label", choiceRenderer.lableOf(model.getObject())).setRenderBodyOnly(true));
		}
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(OnDomReadyHeaderItem.forScript("$('#"+radioGroup.getMarkupId()+" .btn').button()"));
	}
	
	/**
	 * Override do do something to radioGroup
	 * @param radio
	 */
	protected void tuneRadioGroup(final RadioGroup<T> radioGroup) {
		// nops
	}
	
	/**
	 * Override do do something to radio
	 * @param radio
	 */
	protected void tuneRadio(final Radio<T> radio) {
		// nops
	}

	public ISelectionChangeHandler<T> getChangeHandler() {
		return changeHandler;
	}

	public void setChangeHandler(ISelectionChangeHandler<T> handler) {
		this.changeHandler = handler;
	}
}
