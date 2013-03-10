package de.agilecoders.wicket.markup.html.bootstrap.extensions.form;

import java.util.List;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.StringValue;

/**
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * @see http://twitter.github.com/bootstrap/javascript.html#typeahead
 * 
 * @param <T>
 */
abstract public class TypeaheadField<T> extends TextField<T> {

	private static final long serialVersionUID = 1L;

	private AbstractDefaultAjaxBehavior ajaxBehavior;

	private TypeaheadConfig config;

	public TypeaheadField(String id) {
		this(id, null, null, new TypeaheadConfig());
	}
	
	public TypeaheadField(String id, IModel<T> model) {
		this(id, model, null, new TypeaheadConfig());
	}
	
	public TypeaheadField(final String id, IModel<T> model, Class<T> type) {
		this(id, model, type, new TypeaheadConfig());
	}
	
	public TypeaheadField(final String id, IModel<T> model, Class<T> type, TypeaheadConfig config) {
		super(id, model, type);
		this.config = config;
		add(ajaxBehavior = new TypeaheadBehavior());
	}
	
	public TypeaheadField(String id, IModel<T> model, TypeaheadConfig config) {
		super(id, model);
		this.config = config;
		add(ajaxBehavior = new TypeaheadBehavior());
	}

	public TypeaheadField(String id, TypeaheadConfig config) {
		super(id);
		this.config = config;
		add(ajaxBehavior = new TypeaheadBehavior());
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		CharSequence url = ajaxBehavior.getCallbackUrl();
		AppendingStringBuffer buffer = new AppendingStringBuffer("var options = new Object();\n");

		if (config.getMinLength() != null) {
			appendValue(buffer, "options.minLength", config.getMinLength().toString());
		}

		if (config.getItems() != null) {
			appendValue(buffer, "options.items", config.getItems().toString());
		}

		if (config.getMatcher() != null) {
			appendValue(buffer, "options.matcher", config.getMatcher());
		}

		if (config.getSorter() != null) {
			appendValue(buffer, "options.sorter", config.getSorter());
		}

		if (config.getUpdater() != null) {
			appendValue(buffer, "options.updater", config.getUpdater());
		}

		if (config.getHighlighter() != null) {
			appendValue(buffer, "options.highlighter", config.getHighlighter());
		}

		String source = "function(query, process) { $.getJSON('" + url
				+ "&input=' + query, function(data) { process(data); }); }";

		appendValue(buffer, "options.source", source);
		buffer.append(String.format("jQuery('#" + getMarkupId() + "').typeahead(options);"));
		response.render(OnDomReadyHeaderItem.forScript(buffer.toString()));
	}

	private class TypeaheadBehavior extends AbstractDefaultAjaxBehavior {

		private static final long serialVersionUID = 1L;

		@Override
		protected void respond(AjaxRequestTarget target) {

			StringValue value = getRequest().getQueryParameters().getParameterValue("input");
			JSONArray array = new JSONArray(getChoices(value.toString()));
			getRequestCycle().scheduleRequestHandlerAfterCurrent(
					new TextRequestHandler("application/json", "UTF-8", array.toString()));
		}
	}

	protected void appendValue(AppendingStringBuffer buffer, String key, String value) {
		buffer.append(key).append("=").append(value).append(";\n");
	}

	public abstract List<String> getChoices(String input);
}
