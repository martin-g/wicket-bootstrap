package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

/**
 * Tests the {@link ContainerBehavior}
 */
public class ContainerBehaviorTest extends WicketApplicationTest {

    @Test
    public void cssClassnameIsSet() {
        startBehaviorInPage(new ContainerBehavior());

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "container");
    }

}
