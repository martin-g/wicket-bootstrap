package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.ContainerBehavior}
 */
public class RowBehaviorTest extends WicketApplicationTest {

    @Test
    public void defaultLayoutIsFixed() {
        assertThat(new RowBehavior().layout(), is(equalTo(Layout.Fixed)));
    }

    @Test
    public void layoutFixedIsSet() {
        startBehaviorInPage(new RowBehavior(Layout.Fixed));

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "row");
    }

    @Test
    public void layoutFluidIsSet() {
        startBehaviorInPage(new RowBehavior(Layout.Fluid));

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "row-fluid");
    }
}
