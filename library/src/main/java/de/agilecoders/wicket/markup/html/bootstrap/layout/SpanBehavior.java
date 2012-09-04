package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class SpanBehavior extends BootstrapBaseBehavior {

    private SpanType type;
    private Offset offset;

    public SpanBehavior() {
        this.type = SpanType.SPAN12;
        this.offset = Offset.OFFSET0;
    }

    public SpanBehavior(SpanType spanType, Offset offset) {
        this.type = spanType;
        this.offset = offset;
    }

    public SpanType type() {
        return type;
    }

    public SpanBehavior type(SpanType type) {
        this.type = type;

        return this;
    }

    public Offset offset() {
        return offset;
    }

    public SpanBehavior offset(Offset offset) {
        this.offset = offset;

        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        if (type != null) {
            component.add(type().newCssClassNameAppender());
        }

        if (offset != null) {
            component.add(offset().newCssClassNameAppender());
        }
    }
}
