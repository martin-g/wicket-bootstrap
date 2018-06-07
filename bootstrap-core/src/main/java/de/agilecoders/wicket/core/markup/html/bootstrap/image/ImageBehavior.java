package de.agilecoders.wicket.core.markup.html.bootstrap.image;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

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
        Rounded("rounded"), Circle("rounded-circle"), Thumbnail("img-thumbnail"), Responsive("img-fluid"), Default("");

        private final String cssClass;

        private Type(String cssClass) {
            this.cssClass = cssClass;
        }

        @Override
        public String cssClassName() {
            return cssClass;
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

        Components.assertTag(component, tag, "img");
        Attributes.addClass(tag, borderType.cssClassName());
    }

}
