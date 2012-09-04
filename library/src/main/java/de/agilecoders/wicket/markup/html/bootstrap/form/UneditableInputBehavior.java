package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class UneditableInputBehavior extends BootstrapBaseBehavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AssertTagNameBehavior("span"));
        component.add(new CssClassNameAppender("uneditable-input"));
    }
}
