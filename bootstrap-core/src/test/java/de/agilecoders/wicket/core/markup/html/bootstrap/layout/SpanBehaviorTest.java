package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.LargeScreenSpanType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.ExtraSmallOffsetType;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link SpanBehavior}
 *
 * @author miha
 */
class SpanBehaviorTest extends WicketApplicationTest {

    @Test
    void defaultSpanWidthIs12() {
        startBehaviorInPage(new SpanBehavior());

        TagTester tagTester = tester().getTagByWicketId(id());
        assertCssClass(tagTester, "col-lg-12");
        assertNotContainsCssClass(tagTester, "offset0");
    }

    @Test
    void offset0DoesntNeedAClassName() {
        startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.SPAN1, ExtraSmallOffsetType.OFFSET0));

        TagTester tagTester = tester().getTagByWicketId(id());
        assertNotContainsCssClass(tagTester, "offset0");
    }

    @Test
    void spanWidthCanBeChanged() {
        for (int i = 1; i <= 12; i++) {
            startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.valueOf("SPAN" + i)));

            TagTester tagTester = tester().getTagByWicketId(id());
            assertCssClass(tagTester, "col-lg-" + i);
        }
    }

    @Test
    void offsetCanBeChanged() {
        for (int i = 1; i <= 12; i++) {
            startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.SPAN1, ExtraSmallOffsetType.valueOf("OFFSET" + i)));

            TagTester tagTester = tester().getTagByWicketId(id());
            assertCssClass(tagTester, "col-xs-offset-" + i);
        }
    }
}
