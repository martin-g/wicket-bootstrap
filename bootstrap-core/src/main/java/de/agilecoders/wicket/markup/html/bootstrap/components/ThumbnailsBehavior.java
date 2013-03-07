package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * A simple {@link org.apache.wicket.behavior.Behavior} that adds the
 * thumbnails class to an {@code ul} element.
 *
 * @author miha
 */
public class ThumbnailsBehavior extends BootstrapBaseBehavior {

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "thumbnails");
    }

}
