package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import java.util.Arrays;
import java.util.List;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.LargeScreenSpanType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.ExtraSmallOffsetType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.order.LargeOrderType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.order.MediumOrderType;

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
            assertCssClass(tagTester, "offset-" + i);
        }
    }

    @Test
    void order0DoesntNeedAClassName() {
        startBehaviorInPage(new SpanBehavior(LargeScreenSpanType.SPAN1, MediumOrderType.ORDER0));

        TagTester tagTester = tester().getTagByWicketId(id());
        assertNotContainsCssClass(tagTester, MediumOrderType.ORDER0.cssClassName());
    }

    @Test
    void orderCanBeChanged() {
        List<LargeOrderType> orderTypes = Arrays.asList(
                LargeOrderType.ORDER1, LargeOrderType.ORDER2, LargeOrderType.ORDER3,
                LargeOrderType.ORDER4, LargeOrderType.ORDER5, LargeOrderType.ORDER6,
                LargeOrderType.ORDER7, LargeOrderType.ORDER8, LargeOrderType.ORDER9,
                LargeOrderType.ORDER10, LargeOrderType.ORDER11, LargeOrderType.ORDER12,
                LargeOrderType.FIRST, LargeOrderType.LAST
        );
        for (LargeOrderType orderType : orderTypes) {
            assertCssClass(new SpanBehavior(LargeScreenSpanType.SPAN2, orderType), orderType.cssClassName());
        }
    }
}
