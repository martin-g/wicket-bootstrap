package de.agilecoders.wicket.less;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.markup.html.themes.cerulean.CeruleanCssReference;
import org.apache.wicket.resource.JQueryResourceReference;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.containsString;

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
        Resource file = locator.findResource(CeruleanCssReference.class, "less/cerulean.less");

        Assert.assertThat(file.getName(), is(equalTo("cerulean.less")));
        Assert.assertTrue(file.exists());
    }

    @Test
    public void findFileInJar() {
        Resource file = locator.findResource(JQueryResourceReference.class, "jquery/jquery.js");

        Assert.assertThat(file.getName(), is(equalTo("jquery.js")));
        Assert.assertTrue(file.exists());
    }
}
