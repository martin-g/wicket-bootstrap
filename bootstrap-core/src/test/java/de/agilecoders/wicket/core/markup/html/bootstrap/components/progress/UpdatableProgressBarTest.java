package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.junit.jupiter.api.Test;

/**
 * @author sschrader, t8y.com
 */
class UpdatableProgressBarTest extends WicketApplicationTest {

    @Test
    void progressInitialized() {
        final IModel<Integer> model = Model.of(ProgressBar.MIN);
        UpdatableProgressBar progressBar = new UpdatableProgressBar(id(), model) {
            @Override
            protected IModel<Integer> newValue() {
                return Model.of(value() + 1);
            }
        };

        startComponentInPage(progressBar);

        assertEquals(model.getObject(), progressBar.value());

        AjaxSelfUpdatingTimerBehavior updatingTimerBehavior = progressBar.getBehaviors(AjaxSelfUpdatingTimerBehavior.class).get(0);
        tester().executeBehavior(updatingTimerBehavior);
        assertEquals(Integer.valueOf(model.getObject() + 1), progressBar.value());
    }

}
