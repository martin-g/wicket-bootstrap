package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * @author daniel.jipa
 * @reference http://codepen.io/Thomas-Lebeau/pen/csHqx
 */


public class Spinner extends TextField<Integer>{

	private static final long serialVersionUID = -2660832209883037448L;

	private static final CssResourceReference SPINNER_CSS = new CssResourceReference(Spinner.class,
			"css/spinner.css");
	private static final JavaScriptResourceReference SPINNER_JS = new JavaScriptResourceReference(Spinner.class,
			"js/spinner.js");

	private final SpinnerConfig config;

	public Spinner(String id) {
		super(id);
		this.config = new SpinnerConfig();
	}

	public Spinner(String id, SpinnerConfig config) {
		super(id);
		this.config = config;
	}

	public Spinner(String id, final IModel<Integer> model) {
		super(id, model);
		this.config = new SpinnerConfig();
	}

	public Spinner(String id, final IModel<Integer> model, SpinnerConfig config) {
		super(id, model);
		this.config = config;
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		checkComponentTag(tag, "input");
		Attributes.set(tag, "type", "text");
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(SPINNER_CSS));
		response.render(JavaScriptHeaderItem.forReference(SPINNER_JS));
		response.render(OnDomReadyHeaderItem.forScript(createScript(config)));
	}

	@Override
	protected void onInitialize() {
		add(new BootstrapBaseBehavior(){

			private static final long serialVersionUID = -7570348574095299836L;

			@Override
			public void beforeRender(Component component) {
				component.getResponse().write("<div class=\"input-group spinner\">");
				super.beforeRender(component);
			}

			@Override
			public void afterRender(Component component) {
				super.afterRender(component);
				component.getResponse().write("<div class=\"input-group-btn-vertical\"><a class=\"btn btn-primary\"><i class=\"fa fa-caret-up\"></i></a><a class=\"btn btn-primary\"><i class=\"fa fa-caret-down\"></i></a></div></div>");
			}
		});
		super.onInitialize();
	}


	protected CharSequence createScript(final SpinnerConfig config) {
		return $(this).chain("spinner", config).get();
	}

	protected final SpinnerConfig getConfig() {
		return config;
	}
}
