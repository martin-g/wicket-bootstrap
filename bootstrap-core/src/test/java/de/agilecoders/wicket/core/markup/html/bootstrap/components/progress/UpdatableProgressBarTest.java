package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.junit.Test;

/**
 * @author sschrader, t8y.com
 */
public class UpdatableProgressBarTest extends WicketApplicationTest {

    @Test
    public void progressInitialized() {
        UpdatableProgressBar progressBar = new UpdatableProgressBar(id()) {
            @Override
            protected IModel<Integer> newValue() {
                return Model.of(Integer.valueOf((int) (Math.random() * 100)));
            }
        };

        startComponentInPage(progressBar);

        tester().assertUsability(progressBar);
    }

}
