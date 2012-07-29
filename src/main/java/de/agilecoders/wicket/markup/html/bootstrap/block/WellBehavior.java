package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class WellBehavior extends BootstrapBaseBehavior {

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new CssClassNameAppender("well"));
    }
}
