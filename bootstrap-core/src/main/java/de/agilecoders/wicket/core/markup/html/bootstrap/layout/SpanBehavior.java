package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.LargeScreenSpanType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.SpanType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.LargeOffsetType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.OffsetType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.order.LargeOrderType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.order.OrderType;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Controls the width, order and offsetType of a layout element inside a container
 *
 * @author miha
 */
public class SpanBehavior extends BootstrapBaseBehavior {

    private final SpanType type;
    private final OffsetType offsetType;
    private final OrderType orderType;

    /**
     * Construct. Uses {@link LargeOffsetType#OFFSET0}, {@link LargeOrderType#ORDER0} and {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.LargeScreenSpanType#SPAN12}.
     */
    public SpanBehavior() {
        this(LargeScreenSpanType.SPAN12);
    }

    /**
     * Construct. Uses {@link LargeOffsetType#OFFSET0} and {@link LargeOrderType#ORDER0}.
     *
     * @param spanType span width of layout element
     */
    public SpanBehavior(final SpanType spanType) {
        this(spanType, LargeOffsetType.OFFSET0, LargeOrderType.ORDER0);
    }

    /**
     * Construct. Uses {@link LargeOrderType#ORDER0}.
     *
     * @param spanType span width of layout element
     * @param offsetType   offsetType of layout element
     */
    public SpanBehavior(final SpanType spanType, final OffsetType offsetType) {
        this(spanType, offsetType, LargeOrderType.ORDER0);
    }

    /**
     * Construct. Uses {@link LargeOffsetType#OFFSET0}.
     *
     * @param spanType span width of layout element
     * @param orderType orderType of layout element
     */
    public SpanBehavior(final SpanType spanType, final OrderType orderType) {
        this(spanType, LargeOffsetType.OFFSET0, orderType);
    }

    /**
     * Construct. Uses {@link LargeOffsetType#OFFSET0}.
     *
     * @param spanType span width of layout element
     * @param offsetType offsetType of layout element
     * @param orderType orderType of layout element
     */
    public SpanBehavior(final SpanType spanType, final OffsetType offsetType, final OrderType orderType) {
        Args.notNull(spanType, "spanType");
        Args.notNull(offsetType, "offsetType");
        Args.notNull(orderType, "orderType");

        this.type = spanType;
        this.offsetType = offsetType;
        this.orderType = orderType;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, type.cssClassName(), offsetType.cssClassName(), orderType.cssClassName());
    }

}
