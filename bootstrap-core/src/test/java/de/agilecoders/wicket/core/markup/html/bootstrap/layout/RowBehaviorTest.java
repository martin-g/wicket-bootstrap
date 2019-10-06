package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.ContainerBehavior}
 */
class RowBehaviorTest extends WicketApplicationTest {

    @Test
    void cssClassNameIsSet() {
        startBehaviorInPage(new RowBehavior());

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "row");
    }

}
