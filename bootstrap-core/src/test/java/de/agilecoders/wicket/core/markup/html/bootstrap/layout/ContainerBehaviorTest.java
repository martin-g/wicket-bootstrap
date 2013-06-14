package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

/**
 *
 */
public class ContainerBehaviorTest extends WicketApplicationTest {

    @Test
    public void setCssClass() {
        String componentId = "id";
        WebMarkupContainer component = new WebMarkupContainer(componentId);
        ContainerBehavior behavior = new ContainerBehavior(Layout.Fixed);
        component.add(behavior);

        tester().startComponentInPage(component);
        TagTester tagTester = tester().getTagByWicketId(componentId);
        assertCssClass(tagTester, "container");

        behavior.layout(Layout.Fluid);
        tester().startComponentInPage(component);
        tagTester = tester().getTagByWicketId(componentId);
        assertCssClass(tagTester, "container-fluid");
    }
}
