package de.agilecoders.wicket.markup.html.themes.cerulean;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.themes.bootswatch.BootswatchCssReference;
import de.agilecoders.wicket.settings.IBootstrapLessCompilerSettings;
import de.agilecoders.wicket.test.IntegrationTest;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests the {@link CeruleanCssReferenceTest} class.
 *
 * @author miha
 * @version 1.0
 */
@Ignore
@Category(IntegrationTest.class)
public class CeruleanCssReferenceTest extends WicketApplicationTest {

    private String cssContent;

    @Override
    protected void onBefore() {
        try {
            cssContent = IOUtils.toString(BootswatchCssReference.CERULEAN.getResource().getResourceStream().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ResourceStreamNotFoundException e) {
            throw new RuntimeException(e);
        }

        getBootstrapSettings().getBootstrapLessCompilerSettings()
            .setCacheStrategy(IBootstrapLessCompilerSettings.CacheStrategy.Never)
            .setUseLessCompiler(true);
    }

    @Test
    public void lessFileWasGeneratedWithoutError() {
        BootstrapCssReference ref = BootswatchCssReference.CERULEAN;

        tester().startResourceReference(ref);
        tester().assertNoErrorMessage();
    }

    @Test
    public void lessFileWasGeneratedWithCorrectResource() {
        BootstrapCssReference ref = spy(BootswatchCssReference.CERULEAN);

        tester().startResourceReference(ref);
        verify(ref, times(1)).getResource();
    }

    @Test
    @Ignore // update bootstrap css file
    public void lessFileWasGeneratedWithCorrectContent() {
        BootstrapCssReference ref = BootswatchCssReference.CERULEAN;

        tester().startResourceReference(ref);
        Assert.assertThat(cssContent, is(equalTo(tester().getLastResponseAsString())));
    }

}
