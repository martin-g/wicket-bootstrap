package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

/**
 * A {@link de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar} with
 * self updating Ajax  behavior
 *
 * @author miha
 * @version 1.0
 */
public abstract class UpdatableProgressBar extends ProgressBar {

    Duration updateInterval = Duration.seconds(5);
    private UpdateBehavior behavior;

    public UpdatableProgressBar(String id) {
        this(id, null);
    }

    public UpdatableProgressBar(String id, IModel<Integer> model) {
        super(id, model);

        setOutputMarkupId(true);
        active(true);

        behavior = new UpdateBehavior(updateInterval) {
            @Override
            protected void onPostProcessTarget(AjaxRequestTarget target) {
                super.onPostProcessTarget(target);

                UpdatableProgressBar.this.onPostProcessTarget(target);
            }
        };

        add(behavior);
    }

    public Duration updateInterval() {
        return behavior.updateInterval();
    }

    public UpdatableProgressBar updateInterval(Duration updateInterval) {
        behavior.updateInterval(updateInterval);
        return this;
    }

    protected void onPostProcessTarget(IPartialPageRequestHandler target) {
        value(newValue());

        if (complete()) {
            behavior.stop(target);

            onComplete(target);
        }
    }

    protected void onComplete(IPartialPageRequestHandler target) {
        active(false);
        striped(false);

        target.add(this);
    }

    protected abstract IModel<Integer> newValue();

    /**
     * An extension of AjaxSelfUpdatingTimerBehavior to be able to use
     * #setUpdateInterval() method
     */
    private abstract static class UpdateBehavior extends AjaxSelfUpdatingTimerBehavior {

        /**
         * Construct.
         *
         * @param updateInterval Duration between AJAX callbacks
         */
        private UpdateBehavior(Duration updateInterval) {
            super(updateInterval);
        }

        private void updateInterval(Duration duration) {
            setUpdateInterval(duration);
        }

        private Duration updateInterval() {
            return getUpdateInterval();
        }
    }
}
