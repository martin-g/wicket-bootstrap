package de.agilecoders.wicket.test;

import de.agilecoders.wicket.util.CssClassNames;
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
        CssClassNames.Builder builder = CssClassNames.newBuilder().add(classNames);

        for (String className : classNames) {
            assertThat(builder.contains(className), is(equalTo(true)));
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
