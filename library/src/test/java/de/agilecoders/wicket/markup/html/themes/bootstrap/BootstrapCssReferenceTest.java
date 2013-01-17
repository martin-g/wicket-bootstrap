package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.settings.IBootstrapLessCompilerSettings;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

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
            cssContent = IOUtils.toString(BootstrapCssReference.INSTANCE.getResource().getResourceStream().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ResourceStreamNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected IBootstrapSettings createBootstrapSettings() {
        IBootstrapSettings settings = super.createBootstrapSettings();
        settings.getBootstrapLessCompilerSettings()
            .setUseLessCompiler(true)
            .setCacheStrategy(IBootstrapLessCompilerSettings.CacheStrategy.Never);
        return settings;
    }

    @Test
    public void lessFileWasGeneratedWithoutError() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        tester().assertNoErrorMessage();
    }

    @Test
    public void lessFileWasGeneratedWithCorrectContent() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        Assert.assertThat(tester().getLastResponseAsString(), is(equalTo(cssContent)));
    }
}
