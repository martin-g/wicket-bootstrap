package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.ExtraLargeOffsetType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.OffsetType;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

/**
 * Controls the width and offsetType of a layout element inside a container
 *
 * @author miha
 */
public class SpanBehavior extends BootstrapBaseBehavior {

    private final SpanType type;
    private final OffsetType offsetType;

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.ExtraLargeOffsetType#OFFSET0} and {@link LargeScreenSpanType#SPAN12}.
     */
    public SpanBehavior() {
        this(LargeScreenSpanType.SPAN12);
    }

    /**
     * Construct. Uses {@link de.agilecoders.wicket.core.markup.html.bootstrap.layout.offset.ExtraLargeOffsetType#OFFSET0}.
     *
     * @param spanType span width of layout element
     */
    public SpanBehavior(final SpanType spanType) {
        this(spanType, ExtraLargeOffsetType.OFFSET0);
    }

    /**
     * Construct.
     *
     * @param spanType span width of layout element
     * @param offsetType   offsetType of layout element
     */
    public SpanBehavior(final SpanType spanType, final OffsetType offsetType) {
        Args.notNull(spanType, "spanType");
        Args.notNull(offsetType, "offsetType");

        this.type = spanType;
        this.offsetType = offsetType;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, type.cssClassName(), offsetType.cssClassName());
    }

}
