package de.agilecoders.wicket.less;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.apache.wicket.resource.JQueryResourceReference;
import org.junit.Assert;
import org.junit.Test;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.markup.html.themes.bootswatch.BootswatchCssReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ResourceLocatorTest extends WicketApplicationTest {

    private ResourceLocator locator;

    @Override
    public void onBefore() {
        locator = new ResourceLocator();
    }

    @Test
    public void findFile() {
        Resource file = locator.findResource(BootswatchCssReference.class, "less/cerulean/bootswatch.less");

        Assert.assertThat(file.getName(), is(equalTo("bootswatch.less")));
        Assert.assertTrue(file.exists());
    }

    @Test
    public void findFileInJar() {
        Resource file = locator.findResource(JQueryResourceReference.class, "jquery/jquery.js");

        Assert.assertThat(file.getName(), is(equalTo("jquery.js")));
        Assert.assertTrue(file.exists());
    }
}
