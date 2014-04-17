package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class UpdatableProgressBar extends UploadProgressBar {

    Duration updateInterval = Duration.seconds(5);
    private UpdateBehavior behavior;

    public UpdatableProgressBar(String id) {
        super(id);
    }

    public UpdatableProgressBar(String id, IModel<Integer> model) {
        super(id, model);
    }

    public Duration updateInterval() {
        return behavior.updateInterval();
    }

    public UpdatableProgressBar updateInterval(Duration updateInterval) {
        behavior.updateInterval(updateInterval);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
        active(true);
        striped(true);

        behavior = new UpdateBehavior(updateInterval) {
            @Override
            protected void onPostProcessTarget(AjaxRequestTarget target) {
                super.onPostProcessTarget(target);

                UpdatableProgressBar.this.onPostProcessTarget(target);
            }
        };

        indicator().add(behavior);
    }

    protected void onPostProcessTarget(AjaxRequestTarget target) {
        value(newValue());

        if (complete()) {
            behavior.stop(target);

            onComplete(target);
        }
    }

    protected void onComplete(AjaxRequestTarget target) {
        active(false);
        striped(false);

        target.add(this);
    }

    protected abstract IModel<Integer> newValue();

    /**
     *
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
