package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import static de.agilecoders.wicket.jquery.JQuery.$;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * Bootstrap Rating is a jQuery plugin that creates a rating control that uses Bootstrap glyphicons for rating symbols. 
 * http://dreyescat.github.io/bootstrap-rating
 * @author daniel.jipa
 */
public class Rating<T> extends HiddenField<T>{

	private static final long serialVersionUID = 3927736784638450199L;
	
	private static final Logger LOG = LoggerFactory.getLogger(Rating.class);
	
	private static final CssResourceReference RATING_CSS = new CssResourceReference(Rating.class,
			"css/bootstrap-rating.css");
	private static final JavaScriptResourceReference RATING_JS = new JavaScriptResourceReference(Rating.class,
			"js/bootstrap-rating.min.js") {

		private static final long serialVersionUID = -7280204736754448532L;

		@Override
		public List<HeaderItem> getDependencies() {
			final Application application = Application.get();
			List<HeaderItem> his = new ArrayList<HeaderItem>();
			his.add(JavaScriptHeaderItem.forReference(application.getJavaScriptLibrarySettings()
		            .getJQueryReference()));
			return his;
		}
	};

	private final RatingConfig config;
	
	public Rating(String id) {
        this(id, null, new RatingConfig());
    }

    public Rating(String id, RatingConfig config) {
        this(id, null, config);
    }

    public Rating(String id, final IModel<T> model) {
        this(id, model, new RatingConfig());
    }

    public Rating(String id, final IModel<T> model, RatingConfig config) {
        super(id, model);
        this.config = config;
    }
	
    @Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(RATING_CSS));
		response.render(JavaScriptHeaderItem.forReference(RATING_JS));
		response.render(OnDomReadyHeaderItem.forScript(createScript(getConfig())));
	}
    
    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	registerChangeListener();
    }
    
    private void registerChangeListener() {
        add(new AjaxEventBehavior("change"){

        	private static final long serialVersionUID = -8135111313923624591L;

			@Override
            protected void onEvent(AjaxRequestTarget target) {
				if (wantChangeListener()){
					LOG.info("change rate triggered");
	                onChange(target);
				}
            }
        });
    }
    
	protected boolean wantChangeListener() {
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
