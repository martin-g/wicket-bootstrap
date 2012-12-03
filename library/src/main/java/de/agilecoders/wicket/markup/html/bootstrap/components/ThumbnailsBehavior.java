package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;

/**
 * A simple {@link org.apache.wicket.behavior.Behavior} that adds the
 * thumbnails class to an {@code ul} element.
 *
 * @author miha
 * @version 1.0
 */
public class ThumbnailsBehavior extends BootstrapBaseBehavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new CssClassNameAppender("thumbnails"));
    }
}
