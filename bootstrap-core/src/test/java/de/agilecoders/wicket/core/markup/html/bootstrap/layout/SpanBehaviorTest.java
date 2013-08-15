package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

/**
 * Tests the {@link SpanBehavior}
 *
 * @author miha
 */
public class SpanBehaviorTest extends WicketApplicationTest {

    @Test
    public void defaultSpanWidthIs12() {
        startBehaviorInPage(new SpanBehavior());

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "col-lg-12");
        assertNotContainsCssClass(tagTester, "offset0");
    }

    @Test
    public void offset0DoesntNeedAClassName() {
        startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.SPAN1, Offset.OFFSET0));

        TagTester tagTester = tester().getTagByWicketId(id());
        assertNotContainsCssClass(tagTester, "offset0");
    }

    @Test
    public void spanWidthCanBeChanged() {
        for (int i = 1; i <= 12; i++) {
            startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.valueOf("SPAN" + i)));

            TagTester tagTester = tester().getTagByWicketId(id());
            assertCssClass(tagTester, "col-lg-" + i);
        }
    }

    @Test
    public void offsetCanBeChanged() {
        for (int i = 1; i <= 12; i++) {
            startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.SPAN1, Offset.valueOf("OFFSET" + i)));

            TagTester tagTester = tester().getTagByWicketId(id());
            assertCssClass(tagTester, "offset" + i);
        }
    }
}
