package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link BootstrapCssReference} class.
 *
 * @author miha
 */
public class BootstrapCssReferenceTest extends WicketApplicationTest {
    private String cssContent;

    @Override
    protected void onBefore() {
        try {
            cssContent = IOUtils.toString(BootstrapCssReference.instance().getResource().getResourceStream().getInputStream());
        } catch (IOException | ResourceStreamNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void lessFileWasGeneratedWithoutError() {
        BootstrapCssReference ref = BootstrapCssReference.instance();

        tester().startResourceReference(ref);
        tester().assertNoErrorMessage();
    }

    @Test
    void lessFileWasGeneratedWithCorrectContent() {
        BootstrapCssReference ref = BootstrapCssReference.instance();

        tester().startResourceReference(ref);
        assertThat(tester().getLastResponseAsString(), is(equalTo(cssContent)));
    }
}
