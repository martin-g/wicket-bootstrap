package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * A Badge is a unread counter or label with rounded corners.
 *
 * @author miha
 */
public class BadgeBehavior extends Behavior {

    private Type type;

    private boolean isPill;

    public BadgeBehavior() {
        this(Type.Secondary, false);
    }

    public BadgeBehavior(Type type) {
        this(type, false);
    }

    public BadgeBehavior(Type type, boolean isPill) {
        this.type = type;
        this.isPill = isPill;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span", "a");
        Attributes.addClass(tag, className(), type.cssClassName());
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
        return  "badge-pill";
    }

    protected String className() {
        return "badge";
    }

    public BadgeBehavior pill(boolean isPill) {
        this.isPill = isPill;

        return this;
    }

    public BadgeBehavior type(Type type) {
        this.type = type;

        return this;
    }

    public enum Type {
        Primary("primary"),
        Secondary("secondary"),
        Success("success"),
        Danger("danger"),
        Warning("warning"),
        Info("info"),
        Light("light"),
        Dark("dark");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String cssClassName() {
            return String.format("badge-%s", value);
        }
    }
}
