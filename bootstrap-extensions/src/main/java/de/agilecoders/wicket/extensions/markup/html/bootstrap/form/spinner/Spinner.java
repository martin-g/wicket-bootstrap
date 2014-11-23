package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Attributes;

/**
 * A mobile and touch friendly input spinner component for Bootstrap 3. It supports the mousewheel and the up/down keys.
 * Integrates <a href="http://www.virtuosoft.eu/code/bootstrap-touchspin/">Bootstrap TouchSpin</a> plugin.
 *
 * @author daniel.jipa
 */
public class Spinner<T extends Number> extends TextField<T>{

	private static final long serialVersionUID = -2660832209883037448L;

	private static final CssResourceReference SPINNER_CSS = new CssResourceReference(Spinner.class,
			"css/touchspin.min.css");
	private static final JavaScriptResourceReference SPINNER_JS = new JavaScriptResourceReference(Spinner.class,
			"js/touchspin.min.js");

	private final SpinnerConfig config;

	public Spinner(String id) {
		this(id, null, new SpinnerConfig());
	}

	public Spinner(String id, SpinnerConfig config) {
		this(id, null, config);
	}

	public Spinner(String id, final IModel<T> model) {
		this(id, model, new SpinnerConfig());
	}

	public Spinner(String id, final IModel<T> model, SpinnerConfig config) {
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

	protected CharSequence createScript(final SpinnerConfig config) {
		return $(this).chain("TouchSpin", getConfig()).get();
	}

	public SpinnerConfig getConfig() {
		return config;
	}
}
