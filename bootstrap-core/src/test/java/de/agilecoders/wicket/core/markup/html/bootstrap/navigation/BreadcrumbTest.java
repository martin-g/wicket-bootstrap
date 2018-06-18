package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import java.util.List;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.Breadcrumb;
import de.agilecoders.wicket.core.test.Attributes;
import de.agilecoders.wicket.core.test.IntegrationTest;

import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.MarkupFragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
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
                tag.setName("nav");
                super.onComponentTag(tag);
            }
        };

        tester().startComponentInPage(breadcrumb);
        final TagTester tester = tester().getTagByWicketId("breadcrumb");

        Attributes.assertClassNamesPresent(tester, "breadcrumb");
    }

    @Test
    public void breadcrumbItemClassIsRendered() {
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
