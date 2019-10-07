package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.Attributes;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                tag.setName("nav");
                super.onComponentTag(tag);
            }
        };

        tester().startComponentInPage(breadcrumb);
        final TagTester tester = tester().getTagByWicketId("breadcrumb");

        Attributes.assertClassNamesPresent(tester, "breadcrumb");
    }

    @Test
    void breadcrumbItemClassIsRendered() {
        final Breadcrumb breadcrumb = new Breadcrumb("breadcrumb");
        breadcrumb.setActive(new BreadCrumbPanel("crumb", breadcrumb) {
            @Override
            public IModel<String> getTitle() {
                return Model.of("Test");
            }
        });

        startComponentInPage(breadcrumb, "<nav wicket:id='breadcrumb'></nav>");

        List<TagTester> tags = tester().getTagsByWicketId("crumbs");
        for (TagTester tag : tags) {
            Attributes.assertClassNamesPresent(tag, "breadcrumb-item");
        }
    }
}
