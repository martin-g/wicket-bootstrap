package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

/**
 * Tests the {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.ContainerBehavior}
 */
public class RowBehaviorTest extends WicketApplicationTest {

    @Test
    public void cssClassNameIsSet() {
        startBehaviorInPage(new RowBehavior());

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "row");
    }

}
