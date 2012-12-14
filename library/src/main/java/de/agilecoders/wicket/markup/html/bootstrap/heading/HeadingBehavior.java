package de.agilecoders.wicket.markup.html.bootstrap.heading;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import org.apache.wicket.Component;

/**
 * Simple heading behavior that only asserts correct tag name and
 * includes the correct css files
 *
 * @author miha
 */
public class HeadingBehavior extends BootstrapBaseBehavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("h1", "h2", "h3", "h4", "h5", "h6"));
    }
}
