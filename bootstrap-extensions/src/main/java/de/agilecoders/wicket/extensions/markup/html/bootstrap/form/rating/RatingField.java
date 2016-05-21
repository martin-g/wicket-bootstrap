package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstrap Rating is a jQuery plugin that creates a rating control that uses
 * Bootstrap glyphicons for rating symbols.
 * http://dreyescat.github.io/bootstrap-rating
 *
 * @author daniel.jipa
 */
public class RatingField<T> extends HiddenField<T> {

	private static final long serialVersionUID = 3927736784638450199L;

	private static final Logger LOG = LoggerFactory.getLogger(RatingField.class);

	private final RatingConfig config;

	public RatingField(String id) {
		this(id, null, new RatingConfig());
	}

	public RatingField(String id, RatingConfig config) {
		this(id, null, config);
	}

	public RatingField(String id, final IModel<T> model) {
		this(id, model, new RatingConfig());
	}

	public RatingField(String id, final IModel<T> model, RatingConfig config) {
		super(id, model);
		Args.notNull(config, "config");
		this.config = config;
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(RatingCssResourceReference.getInstance()));
		response.render(JavaScriptHeaderItem.forReference(RatingJavaScriptResourceReference.getInstance()));
		response.render(OnDomReadyHeaderItem.forScript(createScript(getConfig())));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (wantAjaxNotification()) {
			registerChangeListener();
		}
	}

	private void registerChangeListener() {
		add(new AjaxFormComponentUpdatingBehavior("change") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				LOG.debug("change rate triggered");
				onChange(target);
			}
		});
	}

	protected boolean wantAjaxNotification() {
		return false;
	}

	protected void onChange(AjaxRequestTarget target) {

	}

	protected CharSequence createScript(final RatingConfig config) {
		return $(this).chain("rating", config).get();
	}

	public RatingConfig getConfig() {
		return config;
	}
}
