package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.common.AssertTagNameBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class QuoteBehavior extends AssertTagNameBehavior {

    private boolean pullRight = false;

    public QuoteBehavior() {
        super("blockquote");
    }

    public QuoteBehavior pullRight() {
        pullRight = true;

        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (pullRight) {
            component.add(new CssClassNameAppender("pull-right"));
        }
    }
}
