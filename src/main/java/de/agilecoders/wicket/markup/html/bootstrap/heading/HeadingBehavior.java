package de.agilecoders.wicket.markup.html.bootstrap.heading;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class HeadingBehavior extends BootstrapBaseBehavior {
    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("h1", "h2", "h3", "h4", "h5", "h6"));
    }
}
