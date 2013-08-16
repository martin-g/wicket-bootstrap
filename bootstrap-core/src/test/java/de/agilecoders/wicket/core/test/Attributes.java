package de.agilecoders.wicket.core.test;

import org.apache.wicket.util.tester.TagTester;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Helper class to test attributes
 *
 * @author miha
 */
public final class Attributes {

    /**
     * checks if all given class names was set to tag
     *
     * @param tester     The tag tester of tag to test
     * @param classNames The class names that must be present
     */
    public static void assertClassNamesPresent(final TagTester tester, final String... classNames) {
        String markup = tester.getMarkup();

        for (String className : classNames) {
            assertThat(markup.contains(className), is(equalTo(true)));
        }
    }

    /**
     * checks if given class name is the only one that was set.
     *
     * @param tester    The tag tester of tag to test
     * @param className The class name that must be present
     */
    public static void assertSingleClassNamePresent(TagTester tester, String className) {
        assertThat(tester.getAttribute("class"), is(equalTo(className)));
    }
}
