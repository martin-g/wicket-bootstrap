package de.agilecoders.wicket.markup.html.bootstrap.extensions.tour;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;

/**
 * A behavior that contributes the resources needed for
 * <a href="http://sorich87.github.com/bootstrap-tour/">Bootstrap Tour</a>.
 */
public class TourBehavior extends Behavior {

    private final List<TourStep> steps = new ArrayList<TourStep>();

	/**
	 * Adds a step to the tour
	 *
	 * @param step The tour step
	 * @return {@code this} object, for chaining.
	 */
    public TourBehavior addStep(TourStep step) {
	    Args.notNull(step, "step");
        steps.add(step);
        return this;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        if (steps.size() > 0) {
            response.render(JavaScriptHeaderItem.forReference(BootstrapTourJsReference.INSTANCE));

            StringBuilder js = new StringBuilder();

            js.append("(function() { var tour = new Tour();");
            for (TourStep step : steps) {
                js.append("tour.addStep(").append(step.toJsonString()).append(");");
            }
            js.append("tour.start();");
            js.append(createExtraConfig());
            js.append("})()");
            response.render(OnDomReadyHeaderItem.forScript(js));
        }
    }

	/**
	 * Allows contributing more JavaScript related to the tour.
	 * @return extra tour related JavaScript
	 */
    protected CharSequence createExtraConfig()
    {
        return "";
    }
}
