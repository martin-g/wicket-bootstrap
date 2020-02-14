package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import java.time.Duration;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;

/**
 * A {@link de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar} with
 * self updating Ajax  behavior
 *
 * @author miha
 * @version 1.0
 */
public abstract class UpdatableProgressBar extends ProgressBar {
    private static final long serialVersionUID = 1L;
    Duration updateInterval = Duration.ofSeconds(5L);
    private UpdateBehavior behavior;

    public UpdatableProgressBar(String id) {
        this(id, null);
    }

    public UpdatableProgressBar(String id, IModel<Integer> model) {
        this(id, model, BackgroundColorBehavior.Color.Secondary, false);
    }

    public UpdatableProgressBar(String id, IModel<Integer> model, BackgroundColorBehavior.Color color, boolean labeled) {
        super(id, model, color, labeled);

        setOutputMarkupId(true);
        active(true);

        behavior = new UpdateBehavior(updateInterval) {
            private static final long serialVersionUID = 1L;

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

    public UpdatableProgressBar stop(IPartialPageRequestHandler target) {
        behavior.stop(target);
        return this;
    }

    public UpdatableProgressBar restart(IPartialPageRequestHandler target) {
        behavior.restart(target);
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
        private static final long serialVersionUID = 1L;

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
