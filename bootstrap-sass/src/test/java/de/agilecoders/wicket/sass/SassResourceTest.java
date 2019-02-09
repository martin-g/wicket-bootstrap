package de.agilecoders.wicket.sass;

import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class SassResourceTest extends Assertions {

    /**
     * Tests the compilation of Sass resources to Css.
     * Makes a request to Sass resource and asserts that an expected Css
     * content is being returned.
     * The Sass resource imports another one via "@import 'some.scss'"
     *
     * Can fail because of possible floating number rounding differences in libsass:
     * https://github.com/sass/libsass/issues/2294
     */
    @Test
    public void request() throws IOException {

        WicketTester tester = new WicketTester(new TestApplication());
        tester.startResourceReference(new SassResourceReference(HomePage.class, "resources/root.scss"));
        CharSequence cssContent = tester.getLastResponseAsString();
        cssContent = Strings.replaceAll(cssContent, "\r", "");

        InputStream expectedInputStream = SassResourceTest.class.getResourceAsStream("resources/expected.css");
        CharSequence expected = IOUtils.toString(expectedInputStream);
        expected = Strings.replaceAll(expected, "\r", "");
        assertEquals(expected, cssContent);
    }
}
