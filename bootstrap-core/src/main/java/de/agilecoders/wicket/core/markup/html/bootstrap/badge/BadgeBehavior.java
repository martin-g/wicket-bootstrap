package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * A Badge is a unread counter or label with rounded corners.
 *
 * @author miha
 */
public class BadgeBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private BackgroundColor backgroundColor;

    private boolean isPill;

    public BadgeBehavior() {
        this(BackgroundColor.Secondary, false);
    }

    public BadgeBehavior(BackgroundColor backgroundColor) {
        this(backgroundColor, false);
    }

    public BadgeBehavior(BackgroundColor backgroundColor, boolean isPill) {
        this.backgroundColor = backgroundColor;
        this.isPill = isPill;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span", "a");
        Attributes.addClass(tag, className(), backgroundColor.cssClassName());
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
        return "rounded-pill";
    }

    protected String className() {
        return "badge";
    }

    public BadgeBehavior pill(boolean isPill) {
        this.isPill = isPill;

        return this;
    }

    public BadgeBehavior color(BackgroundColor color) {
        this.backgroundColor = backgroundColor;
        return this;
    }
}
