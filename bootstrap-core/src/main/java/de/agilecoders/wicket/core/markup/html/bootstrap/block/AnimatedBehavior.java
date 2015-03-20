package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 *
 * * #### Description
 *
 * Just-add-water CSS animation animate.css is a bunch of cool, fun, and
 * cross-browser animations for you to use in your projects. Great for emphasis,
 * home pages, sliders, and general just-add-water-awesomeness. Integrates
 * Daniel Eden's work: http://daneden.github.io/animate.css/
 *
 * #### Usage
 *
 * ```java Label label = new Label("id", Model.of("Animate me")); label.add(new
 * AnimatedBehavior(Animation.rollIn)); ```
 *
 * ```html <span wicket:id="id"></span> ```
 *
 * @author daniel.jipa
 */

public class AnimatedBehavior extends Behavior {

	private static final long serialVersionUID = 1L;

	private static final String CDN_PATTERN = "//cdnjs.cloudflare.com/ajax/libs/animate.css/%s/animate.min.css";

	private final Animation animation;
	private String cdnUrl;

	public AnimatedBehavior(Animation animation) {
		this.animation = animation;
	}

	@Override
	public void renderHead(final Component component,
			final IHeaderResponse response) {
		super.renderHead(component, response);
		if (useCdnResources()){
			if (cdnUrl == null) {
                cdnUrl = String.format(CDN_PATTERN, getCdnVersion());
            }
			response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse(cdnUrl))));
		}else{
			response.render(CssHeaderItem
					.forReference(new WebjarsCssResourceReference(
							"animate.css/current/animate.css")));
		}
	}

	protected String getCdnVersion() {
		return "3.2.5";
	}

	@Override
	public void onComponentTag(final Component component, final ComponentTag tag) {
		super.onComponentTag(component, tag);
		Attributes.addClass(tag, "animated", animation.cssClassName());
	}

	@Override
	public void bind(final Component component) {
		super.bind(component);
		BootstrapBaseBehavior.addTo(component);
	}

	@Override
	public void unbind(final Component component) {
		super.unbind(component);
		BootstrapBaseBehavior.removeFrom(component);
	}

	private boolean useCdnResources() {
		boolean result = false;

		if (Application.exists()) {
			final IBootstrapSettings settings = Bootstrap.getSettings();
			result = settings.useCdnResources();
		}

		return result;
	}

	public enum Animation implements ICssClassNameProvider {
		bounce, flash, pulse, rubberBand, shake, swing, tada, wabble, bounceIn, bounceInDown, bounceInLeft, bounceInRight, bounceInUp, bounceOut, bounceOutDown, bounceOutLeft, bounceOutRight, bounceOutUp, fadeIn, fadeInDown, fadeInDownBig, fadeInLeft, fadeInLeftBig, fadeInRight, fadeInRightBig, fadeInUp, fadeInUpBig, fadeOut, fadeOutDown, fadeOutDownBig, fadeOutLeft, fadeOutLeftBig, fadeOutRight, fadeOutRightBig, fadeInUpDown, fadeOutUpBig, flip, flipInX, flipInY, flipOutX, flipOutY, lightSpeedIn, lightSpeedOut, rotateInDownLeft, rotateInDownRight, rotateInUpLeft, rotateInUpRight, rotateOutDownLeft, rotateOutDownRight, rotateOutUpLeft, rotateOutUpRight, slideInUp, slideInDown, slideInLeft, slideInRight, slideOutUp, slideOutDown, slideOutLeft, slideOutRight, zoomIn, zoomInDown, zoomInLeft, zoomInRight, zoomInUp, zoomOut, zoomOutDown, zoomOutLeft, zoomOutRight, zoomOutUp, hinge, rollIn, rollOut;

		@Override
		public String cssClassName() {
			return name();
		}
	}
}
