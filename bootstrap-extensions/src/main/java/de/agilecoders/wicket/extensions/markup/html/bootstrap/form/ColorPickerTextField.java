package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestHandler;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavaScriptResponse;
import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstrap ColorPicker from http://www.eyecon.ro/bootstrap-colorpicker/
 * @author ceefour
 */
@SuppressWarnings("serial")
public class ColorPickerTextField extends TextField<String> {

	private static JavaScriptResourceReference colorpickerJs = new JavaScriptResourceReference(
			ColorPickerTextField.class, "js/colorpicker.js");
	private static CssResourceReference colorpickerCss = new CssResourceReference(
			ColorPickerTextField.class, "css/colorpicker.css");
	/**
	 * bootstrap-colorpicker won't enhance if the textfield was not enabled,
	 * so we must wait until the first time the textfield is enabled.
	 */
	private boolean wasEnhanced = false;
	
	/**
	 * @param id
	 */
	public ColorPickerTextField(String id) {
		super(id, String.class);
	}

	/**
	 * @param id
	 * @param model
	 */
	public ColorPickerTextField(String id, IModel<String> model) {
		super(id, model, String.class);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		add(new AttributeAppender("class", new Model<String>("bootstrap-colorpicker"), " "));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(colorpickerCss));
		response.render(JavaScriptHeaderItem.forReference(colorpickerJs));
		if (isEnabledInHierarchy()) {
			response.render(JavaScriptHeaderItem.forScript("$(document).ready(function() {" +
					"$('#" + getMarkupId() + "').colorpicker();" +
				"});",
				"bootstrap-colorpicker"));
//			wasEnhanced = true; // not working if initially disabled
		}
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
	}
	
	private static final Logger log = LoggerFactory
			.getLogger(ColorPickerTextField.class);
	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);
		if (event.getPayload() instanceof AjaxRequestHandler) {
			final AjaxRequestHandler target = (AjaxRequestHandler) event.getPayload();
			target.addListener(new IListener() {
				@Override
				public void onBeforeRespond(Map<String, Component> map,
						AjaxRequestTarget target) {
				}
				
				@Override
				public void onAfterRespond(Map<String, Component> map,
						IJavaScriptResponse response) {
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
