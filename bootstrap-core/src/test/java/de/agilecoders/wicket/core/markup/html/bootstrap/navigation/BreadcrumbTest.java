package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.Attributes;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Breadcrumb} component
 *
 * @author miha
 */
@IntegrationTest
class BreadcrumbTest extends WicketApplicationTest {

    @Test
    void correctTagNameIsAsserted() {
        final Breadcrumb breadcrumb = new Breadcrumb("breadcrumb") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                tag.setName("div");
                super.onComponentTag(tag);
            }
        };

        assertThrows(MarkupException.class, () -> tester().startComponentInPage(breadcrumb));
    }

    @Test
    void correctClassNameIsSet() {
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
