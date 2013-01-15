package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * Behavior which surrounds an image with a styled border.
 *
 * @author miha
 */
public class ImageBehavior extends BootstrapBaseBehavior {

    /**
     * Holder class for all possible border types
     */
    public enum Type implements ICssClassNameProvider {
        Rounded, Circle, Polaroid, Default;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "img-" + name().toLowerCase();
        }

    }

    private final Type borderType;

    /**
     * Construct.
     *
     * @param borderType The {@link Type} of the border
     */
    public ImageBehavior(Type borderType) {
        this.borderType = borderType;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, borderType.cssClassName());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("img"));
    }
}
