package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.test.Attributes;
import de.agilecoders.wicket.test.IntegrationTest;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Tests the {@link Breadcrumb} component
 *
 * @author miha
 */
@Category(IntegrationTest.class)
public class BreadcrumbTest extends WicketApplicationTest {

    @Test(expected = MarkupException.class)
    public void correctTagNameIsAsserted() {
        final Breadcrumb breadcrumb = new Breadcrumb("breadcrumb") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                tag.setName("div");
                super.onComponentTag(tag);
            }
        };

        tester().startComponentInPage(breadcrumb);
    }

    @Test
    public void correctClassNameIsSet() {
        final Breadcrumb breadcrumb = new Breadcrumb("breadcrumb") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                tag.setName("ul");
                super.onComponentTag(tag);
            }
        };

        tester().startComponentInPage(breadcrumb);
        final TagTester tester = tester().getTagByWicketId("breadcrumb");

        Attributes.assertClassNamesPresent(tester, "breadcrumb");
    }
}
