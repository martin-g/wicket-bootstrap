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
public class HeroBehavior extends AssertTagNameBehavior {

    private boolean pullRight = false;

    public HeroBehavior() {
        super("blockquote");
    }

    public HeroBehavior pullRight() {
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
