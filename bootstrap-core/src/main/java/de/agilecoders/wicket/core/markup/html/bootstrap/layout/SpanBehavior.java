package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

/**
 * Controls the width and offset of a layout element inside a container
 *
 * @author miha
 */
public class SpanBehavior extends BootstrapBaseBehavior {

    private final SpanType type;
    private final Offset offset;

    /**
     * Construct. Uses {@link Offset#OFFSET0} and {@link LargeScreenSpanType#SPAN12}.
     */
    public SpanBehavior() {
        this(LargeScreenSpanType.SPAN12);
    }

    /**
     * Construct. Uses {@link Offset#OFFSET0}.
     *
     * @param spanType span width of layout element
     */
    public SpanBehavior(final SpanType spanType) {
        this(spanType, Offset.OFFSET0);
    }

    /**
     * Construct.
     *
     * @param spanType span width of layout element
     * @param offset   offset of layout element
     */
    public SpanBehavior(final SpanType spanType, final Offset offset) {
        Args.notNull(spanType, "spanType");
        Args.notNull(offset, "offset");

        this.type = spanType;
        this.offset = offset;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, type.cssClassName(), offset.cssClassName());
    }

}
