package de.agilecoders.wicket.less;

import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class LessResourceTest extends Assert {

    /**
     * Tests the compilation of Less resources to Css.
     * Makes a request to Less resource and asserts that an expected Css
     * content is being returned.
     * The Less resource imports another one via "@import 'some.less'"
     */
    @Test
    public void request() throws IOException {

        WicketTester tester = new WicketTester(new TestApplication());
        tester.startResourceReference(new LessResourceReference(HomePage.class, "resources/root.less"));
        String cssContent = tester.getLastResponseAsString();

        InputStream expectedInputStream = LessResourceTest.class.getResourceAsStream("resources/expected.css");
        String expected = IOUtils.toString(expectedInputStream);
        assertEquals(expected, cssContent);
    }
}
