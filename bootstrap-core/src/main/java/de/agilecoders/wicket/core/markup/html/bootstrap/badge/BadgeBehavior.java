package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * A Badge is a unread counter or label with rounded corners.
 *
 * @author miha
 */
public class BadgeBehavior extends Behavior {
    private static final long serialVersionUID = 1L;

    private BackgroundColorBehavior.Color color;

    private boolean isPill;

    public BadgeBehavior() {
        this(BackgroundColorBehavior.Color.Secondary, false);
    }

    public BadgeBehavior(BackgroundColorBehavior.Color color) {
        this(color, false);
    }

    public BadgeBehavior(BackgroundColorBehavior.Color color, boolean isPill) {
        this.color = color;
        this.isPill = isPill;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span", "a");
        Attributes.addClass(tag, className(), color.cssClassName());
        if (isPill) {
            Attributes.addClass(tag, pillClassName());
        }
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }

    protected String pillClassName() {
        return  "rounded-pill";
    }

    protected String className() {
        return "badge";
    }

    public BadgeBehavior pill(boolean isPill) {
        this.isPill = isPill;

        return this;
    }

    public BadgeBehavior color(BackgroundColorBehavior.Color color) {
        this.color = color;

        return this;
    }
}
