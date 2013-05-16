package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavaScriptResponse;
import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import java.util.Map;

/**
 * Bootstrap ColorPicker from http://www.eyecon.ro/bootstrap-colorpicker/
 * @author ceefour
 */
public class ColorPickerTextField extends TextField<String> {

	private static final long serialVersionUID = 1L;

	/**
	 * bootstrap-colorpicker won't enhance if the textfield was not enabled,
	 * so we must wait until the first time the textfield is enabled.
	 */
	private boolean wasEnhanced = false;
	
	/**
	 * @param id
	 */
	public ColorPickerTextField(String id) {
		this(id, null);
	}

	/**
	 * @param id
	 * @param model
	 */
	public ColorPickerTextField(String id, IModel<String> model) {
		super(id, model, String.class);

        setOutputMarkupId(true);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem.forReference(ColorPickerTextFieldCssReference.instance()));
		response.render(JavaScriptHeaderItem.forReference(ColorPickerTextFieldJavaScriptReference.instance()));
		if (isEnabledInHierarchy()) {
			response.render(OnDomReadyHeaderItem.forScript(
                    String.format("$('#%s').colorpicker();});", getMarkupId())));
//			wasEnhanced = true; // not working if initially disabled
		}
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		Attributes.addClass(tag, "bootstrap-colorpicker");
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);
		if (event.getPayload() instanceof AjaxRequestHandler) {
			final AjaxRequestHandler target = (AjaxRequestHandler) event.getPayload();
			target.addListener(new IListener() {
				@Override
				public void onBeforeRespond(Map<String, Component> map, AjaxRequestTarget target) {
				}
				
				@Override
				public void onAfterRespond(Map<String, Component> map, IJavaScriptResponse response) {
					if (isEnabledInHierarchy() && !wasEnhanced) {
						response.addJavaScript("$('#" + getMarkupId() + "').colorpicker();");
						wasEnhanced = true;
					} else if (!isEnabledInHierarchy()) {
						// we need to enhance again when/if this component is enabled later
						wasEnhanced = false;
					}
				}
			});
		}
	}

}
