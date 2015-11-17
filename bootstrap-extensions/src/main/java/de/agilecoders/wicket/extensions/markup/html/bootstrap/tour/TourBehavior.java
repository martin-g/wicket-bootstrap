package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;

import java.util.ArrayList;
import java.util.List;

/**
 * A behavior that contributes the resources needed for
 * <a href="http://sorich87.github.com/bootstrap-tour/">Bootstrap Tour</a>.
 */
public class TourBehavior extends BootstrapJavascriptBehavior {

    private final List<TourStep> steps = new ArrayList<>();

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

    /**
     * @return The number of the steps in this tour
     */
    public final int size() {
        return steps.size();
    }

    /**
     * @return the steps
     */
    public List<TourStep> getSteps() {
        return this.steps;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        if (steps.size() > 0) {
            response.render(JavaScriptHeaderItem.forReference(BootstrapTourJsReference.INSTANCE));

            StringBuilder js = new StringBuilder();
            js.append("(function() { var tour = new Tour(").append(getTourOptions().toJsonString()).append(");");
            for (TourStep step : steps) {
                js.append("tour.addStep(").append(step.toJsonString()).append(");");
            }
            js.append(createExtraConfig());
            js.append("})()");
            response.render(OnDomReadyHeaderItem.forScript(js));
        }
    }

    /**
     * Allows contributing more JavaScript related to the tour. By default starts the tour.
     * @return extra tour related JavaScript
     */
    protected CharSequence createExtraConfig() {
        return "tour.start();";
    }

    /**
     * Overwrite this method to modify the default tour behavior
     * @return the tour options
     */
    protected TourOptions getTourOptions() {
        return new TourOptions();
    }
}
