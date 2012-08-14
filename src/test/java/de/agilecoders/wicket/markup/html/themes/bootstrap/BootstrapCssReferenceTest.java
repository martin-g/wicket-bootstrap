package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.settings.IBootstrapLessCompilerSettings;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the {@link BootstrapCssReference}.
 *
 * @author miha
 * @version 1.0
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

        getBootstrapSettings().getBootstrapLessCompilerSettings().setCacheStrategy(IBootstrapLessCompilerSettings.CacheStrategy.Never);
        getBootstrapSettings().getBootstrapLessCompilerSettings().setUseLessCompiler(true);
    }

    @Test
    public void lessFileWasGeneratedWithoutError() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        tester().assertNoErrorMessage();
    }

    @Test
    @Ignore // update bootstrap css file
    public void lessFileWasGeneratedWithCorrectContent() {
        BootstrapCssReference ref = BootstrapCssReference.INSTANCE;

        tester().startResourceReference(ref);
        Assert.assertThat(cssContent, is(equalTo(tester().getLastResponseAsString())));
    }
}
